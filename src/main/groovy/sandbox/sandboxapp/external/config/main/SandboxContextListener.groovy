package sandbox.sandboxapp.external.config.main
import org.hibernate.SessionFactory
import org.modelmapper.ModelMapper
import org.modelmapper.jackson.JsonNodeValueReader
import org.springframework.orm.hibernate4.HibernateTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import sandbox.concurrency.AtomicBlock
import sandbox.concurrency.dbBased.hibernate.HibernateAtomicBlock
import sandbox.payroll.EmployeeRepository
import sandbox.payroll.ModelSnapshot
import sandbox.payroll.external.interfaceAdapter.persistence.hibernate.HibernatePersistentModelSnapshot
import sandbox.payroll.external.interfaceAdapter.persistence.hibernate.repository.HibernateEmployeeRepository
import sandbox.smartfactory.SmartFactory
import sandbox.validationNotification.ApplicationValidationNotifier

import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
//TODO remove from here what should be put in payroll external
class SandboxContextListener implements ServletContextListener {

    private smartFactory = SmartFactory.instance()

    @Override
    void contextInitialized(ServletContextEvent sce) {
        ApplicationValidationNotifier.createCurrentListOfListeners()
        configSmartFactory()
    }

    public void configSmartFactory() {
        //TODO using sandbox.** causes an error that must be fixed at smart factory
        def globalConfiguration = smartFactory.configurationFor("**")
        globalConfiguration.put(SessionFactory, getConfiguredSessionFactory())
        def transactionFactory = getTransactionFactory()
        globalConfiguration.put(PlatformTransactionManager, transactionFactory)
        globalConfiguration.put(TransactionTemplate, new TransactionTemplate(transactionFactory))
        globalConfiguration.put(AtomicBlock, new HibernateAtomicBlock())


        def sandBoxConfiguration = smartFactory.configurationFor("sandbox.payroll.**")
        sandBoxConfiguration.put(ModelMapper, getConfiguredModelMapper())


        def employeeRepository = new HibernateEmployeeRepository()
        sandBoxConfiguration.put(EmployeeRepository, employeeRepository)
        sandBoxConfiguration.put(ModelSnapshot, {
            def modelSnapshot = new HibernatePersistentModelSnapshot()
            modelSnapshot.add(employeeRepository)
            return modelSnapshot
        }())
    }

    private SessionFactory getConfiguredSessionFactory() {
        return SandboxApplication.addResources(new org.hibernate.cfg.Configuration())
                .setProperty("dialect", "org.hibernate.dialect.HSQLDialect")
                .setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
                .setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:tsg")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                .setProperty("hibernate.connection.username", "sa")
                .setProperty("hibernate.connection.password", "")
                .setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext")
                .setProperty("hibernate.c3p0.min_size", "5")
                .setProperty("hibernate.c3p0.max_size", "20")
                .setProperty("hibernate.c3p0.timeout", "300")
                .setProperty("hibernate.c3p0.max_statements", "50")
                .setProperty("hibernate.c3p0.idle_test_period", "3000")
                .setProperty("hibernate.show_sql", "true")
                .buildSessionFactory()
    }

    private HibernateTransactionManager getTransactionFactory() {
        new HibernateTransactionManager(SessionFactory.smartNewFor(SandboxContextListener))
    }

    private ModelMapper getConfiguredModelMapper() {
        def mapper = new ModelMapper()
        mapper.getConfiguration().addValueReader(new JsonNodeValueReader())

        return mapper
    }

    @Override
    void contextDestroyed(ServletContextEvent sce) {
        println("destroy")
        ApplicationValidationNotifier.destroyCurrentListOfListeners()
    }
}
