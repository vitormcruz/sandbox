package com.vmc.sandbox.payroll.external.config

import com.vaadin.server.VaadinServlet
import com.vmc.sandbox.validationNotification.external.presentation.servlet.ValidationNotifierFilter
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.FilterHolder
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.webapp.WebAppContext
import spark.servlet.SparkFilter

import javax.servlet.DispatcherType

class PayrollJettyApplication {

    def static private configuredVaadinServletHolder
    def static private configuredSparkFilterHolder

    static {
        configuredVaadinServletHolder = getConfiguredVaadinServletHolder()
        configuredSparkFilterHolder = getConfiguredSparkFilterHolder()
    }

    static void main(String[] args) {
        Server server = new Server(7003);

        def webAppContext = new WebAppContext()
        webAppContext.setContextPath("/sandbox")
        webAppContext.setResourceBase(".")
        webAppContext.setExtractWAR(false)
        webAppContext.setContextPath("/");
        webAppContext.addServlet(configuredVaadinServletHolder, "/VAADIN/*")
        webAppContext.addServlet(configuredVaadinServletHolder, "/payroll/*")
        webAppContext.addFilter(ValidationNotifierFilter,"/*", EnumSet.allOf(DispatcherType))
        webAppContext.addFilter(configuredSparkFilterHolder, "/api/payroll/*", EnumSet.allOf(DispatcherType))

        server.setHandler(webAppContext)
        server.start()
        server.join()
    }

    static ServletHolder getConfiguredVaadinServletHolder() {
        def vaadinServlet = new VaadinServlet()
        def servletVaadinHolder = new ServletHolder(vaadinServlet)
        servletVaadinHolder.setInitParameter("productionMode", "false")
        servletVaadinHolder.setInitParameter("UI", "com.vmc.sandbox.payroll.external.presentation.vaadin.PayrollUI")
        servletVaadinHolder.setInitParameter("async-supported", "true")
        servletVaadinHolder.setInitParameter("org.atmosphere.useWebSocketAndServlet3", "true")
        return servletVaadinHolder
    }

    static FilterHolder getConfiguredSparkFilterHolder() {
        def sparkFilter = new SparkFilter()
        def sparkFilterHolder = new FilterHolder(sparkFilter)
        sparkFilterHolder.setInitParameter("applicationClass", "com.vmc.sandbox.payroll.external.config.PayrollSparkRoutesConfiguration")
        return sparkFilterHolder
    }
}
