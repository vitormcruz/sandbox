package com.vmc.sandbox.payroll.external.config

import com.vaadin.server.VaadinServlet
import com.vmc.sandbox.payroll.external.config.sevletContextConfig.ContextConfigListener
import com.vmc.sandbox.validationNotification.external.presentation.servlet.ValidationNotifierFilter
import org.hibernate.SessionFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.servlet.ServletContextListener

@Configuration
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration)
@EnableTransactionManagement
@ComponentScan("com.vmc.sandbox.payroll")
class PayrollApplication extends SpringBootServletInitializer{

    /**
     * The first method, configure, is used to define this class as the configuration class of spring in a normal
     * web server. The second is used to achieve the same goal and also to make possible for spring to start an embedded
     * web server. It would be better if the main method wasn't necessary.
     */

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.sources(PayrollApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PayrollApplication, args);
    }

    @Bean
    public ServletContextListener appConfigurationListener(){
        return getConfigListener()
    }

    private ContextConfigListener getConfigListener() {
        def configListener = new ContextConfigListener()
        configListener.addConfig(new SpringMVCConfig())
        configListener.addConfig(new HibernateInMemoryConfig())
        configListener
    }

    @Bean
    public ServletRegistrationBean vaadinServlet(){
        ServletRegistrationBean registration = new ServletRegistrationBean(new VaadinServlet(), "/payroll/*", "/VAADIN/*");

        Map<String, String> params = new HashMap<String, String>();
        params.put("productionMode", "false")
        params.put("UI", "com.vmc.sandbox.payroll.external.presentation.vaadin.PayrollUI")
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
        return SessionFactory.smartNewFor(PayrollApplication)
    }
}
