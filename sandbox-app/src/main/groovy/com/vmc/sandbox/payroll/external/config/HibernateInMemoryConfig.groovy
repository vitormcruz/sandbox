package com.vmc.sandbox.payroll.external.config

import com.vmc.sandbox.concurrency.AtomicBlock
import com.vmc.sandbox.concurrency.ModelSnapshot
import com.vmc.sandbox.concurrency.external.persistence.hibernate.HibernateAtomicBlock
import com.vmc.sandbox.payroll.EmployeeRepository
import com.vmc.sandbox.payroll.external.persistence.hibernate.HibernatePersistentModelSnapshot
import com.vmc.sandbox.payroll.external.persistence.hibernate.entity.EntitiesHibernateGenericAdaptation
import com.vmc.sandbox.payroll.external.persistence.hibernate.repository.HibernateEmployeeRepository
import org.detangle.smartfactory.SmartFactory
import org.hibernate.SessionFactory
import org.springframework.orm.hibernate4.HibernateTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

class HibernateInMemoryConfig implements Config{
    private smartFactory = SmartFactory.instance()


    //TODO change, don't know how... yet.
    @Override
    public void  configure() {
        def payRollConfiguration = smartFactory.configurationFor("com.vmc.sandbox.payroll.**")
        def concurrencyConfiguration = smartFactory.configurationFor("com.vmc.sandbox.concurrency.**")
        def sessionFactory = getConfiguredSessionFactory()
        payRollConfiguration.put(SessionFactory, sessionFactory)
        EntitiesHibernateGenericAdaptation.adaptIdentifiableEntities(sessionFactory)
        def transactionFactory = getTransactionFactory()
        payRollConfiguration.put(PlatformTransactionManager, transactionFactory)
        concurrencyConfiguration.put(PlatformTransactionManager, transactionFactory)
        payRollConfiguration.put(TransactionTemplate, new TransactionTemplate(transactionFactory))
        concurrencyConfiguration.put(TransactionTemplate, new TransactionTemplate(transactionFactory))
        payRollConfiguration.put(AtomicBlock, new HibernateAtomicBlock())
        concurrencyConfiguration.put(AtomicBlock, new HibernateAtomicBlock())
        def employeeRepository = new HibernateEmployeeRepository()
        payRollConfiguration.put(EmployeeRepository, employeeRepository)
        payRollConfiguration.put(ModelSnapshot, {
            def modelSnapshot = new HibernatePersistentModelSnapshot()
            modelSnapshot.add(employeeRepository)
            return modelSnapshot
        }())

    }

    private SessionFactory getConfiguredSessionFactory() {
        def configuration = new org.hibernate.cfg.Configuration()
        HibernateResources.listOfMappings().each {configuration.addResource(it)}
        return configuration.setProperty("dialect", "org.hibernate.dialect.HSQLDialect")
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
        new HibernateTransactionManager(SessionFactory.smartNewFor(HibernateInMemoryConfig))
    }

    @Override
    public void tearDown() {

    }
}
