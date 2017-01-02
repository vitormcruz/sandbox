package com.vmc.sandbox.allapps.external.config

import com.vaadin.server.VaadinServlet
import org.hibernate.SessionFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.listener.SimpleMessageListenerContainer
import org.springframework.jms.listener.adapter.MessageListenerAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import com.vmc.sandbox.allapps.external.interfaceAdapter.jms.MessageReceiver
import com.vmc.sandbox.heavyValidation.AsyncHeavyValidation
import com.vmc.sandbox.heavyValidation.JMSAsyncHeavyValidation
import com.vmc.sandbox.payroll.external.config.HibernateInMemoryConfig
import com.vmc.sandbox.payroll.external.config.SmartFactoryConfig
import com.vmc.sandbox.sevletContextConfig.ContextConfigListener
import com.vmc.sandbox.validationNotification.servlet.ValidationNotifierFilter

import javax.jms.ConnectionFactory
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan("com.vmc.sandbox")
class SandboxApplication extends SpringBootServletInitializer{

    def static receiver = new MessageReceiver()
    def static AsyncHeavyValidation asyncHeavyValidation
    def static JmsTemplate jmsTemplate
    public static final String MAIL_BOX = "com.vmc.sandbox-message-box"

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
        servletContext.addListener(getConfigListener())
        servletContext.addListener([sessionCreated : {HttpSessionEvent se -> se.getSession().setMaxInactiveInterval(60*60*24)} ,
                                    sessionDestroyed : {}] as HttpSessionListener)

    }

    private ContextConfigListener getConfigListener() {
        def configListener = new ContextConfigListener()
        configListener.addConfig(SmartFactoryConfig)
        configListener.addConfig(HibernateInMemoryConfig)
        configListener
    }

    @Bean
    public ServletRegistrationBean vaadinServlet(){
        ServletRegistrationBean registration = new ServletRegistrationBean(new VaadinServlet(), "/com.vmc.sandbox/*", "/VAADIN/*");

        Map<String, String> params = new HashMap<String, String>();
        params.put("UI", "com.vmc.sandbox.allapps.external.interfaceAdapter.vaadin.SandboxUI");
        params.put("async-supported", "true")
        params.put("org.atmosphere.useWebSocketAndServlet3", "true")

        registration.setInitParameters(params);
        return registration;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ValidationNotifierFilter());
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return SessionFactory.smartNewFor(SandboxApplication)
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
}
