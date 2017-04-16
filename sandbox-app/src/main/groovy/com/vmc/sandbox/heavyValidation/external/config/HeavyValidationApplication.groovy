package com.vmc.sandbox.heavyValidation.external.config

import com.vaadin.server.VaadinServlet
import com.vmc.sandbox.heavyValidation.AsyncHeavyValidation
import com.vmc.sandbox.heavyValidation.external.messaging.MessageReceiver
import com.vmc.sandbox.heavyValidation.external.messaging.jms.JMSAsyncHeavyValidation
import com.vmc.sandbox.payroll.external.config.SpringMVCConfig
import com.vmc.sandbox.sevletContextConfig.ContextConfigListener
import org.detangle.smartfactory.SmartFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
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

import javax.jms.ConnectionFactory
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

@Configuration
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration)
@EnableTransactionManagement
@ComponentScan("com.vmc.sandbox.heavyValidation")
class HeavyValidationApplication extends SpringBootServletInitializer{

    def static receiver = new MessageReceiver()
    public static final String MAIL_BOX = "com.vmc.sandbox.heavyValidation_message-box"

    /**
     * The first method, configure, is used to define this class as the configuration class of spring in a normal
     * web server. The second is used to achieve the same goal and also to make possible for spring to start an embedded
     * web server. It would be better if the main method wasn't necessary.
     */

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.sources(HeavyValidationApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HeavyValidationApplication, args);
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
        configListener.addConfig(SpringMVCConfig)
        return configListener
    }

    @Bean
    public ServletRegistrationBean vaadinServlet(){
        ServletRegistrationBean registration = new ServletRegistrationBean(new VaadinServlet(), "/heavyValidation/*", "/VAADIN/*");

        Map<String, String> params = new HashMap<String, String>();
        params.put("UI", "com.vmc.sandbox.heavyValidation.external.presentation.vaadin.HeavyValidationUI");
        params.put("async-supported", "true")
        params.put("org.atmosphere.useWebSocketAndServlet3", "true")

        registration.setInitParameters(params);
        return registration;
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

        def heavyValidationConfig = SmartFactory.instance().configurationFor("com.vmc.sandbox.heavyValidation.**")
        heavyValidationConfig.put(JmsTemplate, context.getBean(JmsTemplate.class))
        heavyValidationConfig.put(AsyncHeavyValidation, new JMSAsyncHeavyValidation())
        return container;
    }
}
