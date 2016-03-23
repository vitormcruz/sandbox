package sandbox.sandboxapp.config.main

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vaadin.server.VaadinServlet
import org.hibernate.SessionFactory
import org.junit.runner.notification.RunNotifier
import org.modelmapper.ModelMapper
import org.modelmapper.jackson.JsonNodeValueReader
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.listener.SimpleMessageListenerContainer
import org.springframework.jms.listener.adapter.MessageListenerAdapter
import org.springframework.orm.hibernate4.HibernateTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.support.TransactionTemplate
import sandbox.sandboxapp.interfaceAdapter.jms.MessageReceiver
import sandbox.concurrency.AtomicBlock
import sandbox.concurrency.dbBased.hibernate.HibernateAtomicBlock
import sandbox.heavyValidation.AsyncHeavyValidation
import sandbox.heavyValidation.JMSAsyncHeavyValidation
import sandbox.payroll.business.ModelSnapshot
import sandbox.payroll.business.entity.repository.EmployeeRepository
import sandbox.payroll.interfaceAdapter.persistence.hibernate.HibernatePersistentModelSnapshot
import sandbox.payroll.interfaceAdapter.persistence.hibernate.repository.HibernateEmployeeRepository
import sandbox.smartfactory.SmartFactory
import sandbox.validator.ValidatorTrait

import javax.jms.ConnectionFactory
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan("sandbox")
class SandboxApplication extends SpringBootServletInitializer{

    def static receiver = new MessageReceiver()
    def static AsyncHeavyValidation asyncHeavyValidation
    def static JmsTemplate jmsTemplate
    public static final String MAIL_BOX = "sandbox-message-box"
    private static smartFactory = SmartFactory.instance()
    private static SessionFactory sessionFactory
    private static PlatformTransactionManager transactionManager

    static {

        sessionFactory = addResources(new org.hibernate.cfg.Configuration())
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

        transactionManager = new HibernateTransactionManager(sessionFactory)

        configSmartFactory()
    }

    public static void configSmartFactory() {
        def globalConfiguration = smartFactory.configurationFor("**")
        globalConfiguration.put(PlatformTransactionManager, transactionManager)
        globalConfiguration.put(SessionFactory, sessionFactory)
        globalConfiguration.put(TransactionTemplate, new TransactionTemplate(transactionManager))
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

    private static ModelMapper getConfiguredModelMapper() {
        def mapper = new ModelMapper()
        mapper.getConfiguration().addValueReader(new JsonNodeValueReader())
        return mapper
    }

    /**
     * The first method, configure, is used to define this class as the configuration class of spring in a normal
     * web server. The second is used to achieve the same goal and also to make possible for spring to start an embedded
     * web server. It would be better if the main method wasn't necessary.
     */

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.sources(SandboxApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SandboxApplication, args);
    }

    @Override
    void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext)
        servletContext.setInitParameter("productionMode", "false")
        servletContext.addListener([sessionCreated : {HttpSessionEvent se -> se.getSession().setMaxInactiveInterval(60*60*24)} ,
                                    sessionDestroyed : {}] as HttpSessionListener)

        configSmartFactory()
    }

    @Bean
    public ServletRegistrationBean vaadinServlet(){
        ServletRegistrationBean registration = new ServletRegistrationBean(new VaadinServlet(), "/sandbox/*", "/VAADIN/*");

        Map<String, String> params = new HashMap<String, String>();
        params.put("UI", "sandbox.sandboxapp.interfaceAdapter.vaadin.SandboxUI");
        params.put("async-supported", "true")
        params.put("org.atmosphere.useWebSocketAndServlet3", "true")

        registration.setInitParameters(params);
        return registration;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return sessionFactory
    }

    public static org.hibernate.cfg.Configuration addResources(org.hibernate.cfg.Configuration configuration) {
        configuration.addResource("sandbox/payroll/interfaceAdapter/persistence/hibernate/mapping/Employee.hbm.xml")
    }

    @Bean
    MessageListenerAdapter adapter() {
        MessageListenerAdapter messageListener = new MessageListenerAdapter(receiver);
        messageListener.setDefaultListenerMethod("receiveMessage");
        return messageListener;
    }

    @Bean
    SimpleMessageListenerContainer container(MessageListenerAdapter messageListener,
                                             ConnectionFactory connectionFactory,
                                             ConfigurableApplicationContext context) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setMessageListener(messageListener);
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName(MAIL_BOX);
        jmsTemplate = context.getBean(JmsTemplate.class)
        asyncHeavyValidation = new JMSAsyncHeavyValidation()
        return container;
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.failOnEmptyBeans(false)
        builder.mixIn(ValidatorTrait, MixInIgnoreValidatorTrait)
        return builder;
    }

    public static interface MixInIgnoreValidatorTrait {
        @JsonIgnore public RunNotifier getNotifier();
        @JsonIgnore public Collection getValidations();
    }

}
