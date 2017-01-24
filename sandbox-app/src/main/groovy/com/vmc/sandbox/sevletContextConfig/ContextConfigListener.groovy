package com.vmc.sandbox.sevletContextConfig

import com.vmc.sandbox.payroll.external.config.Config

import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

class ContextConfigListener implements ServletContextListener {

    private Collection<Config> configs = []

    @Override
    void contextInitialized(ServletContextEvent sce) {
        configs.each {it.configure()}
    }

    @Override
    void contextDestroyed(ServletContextEvent sce) {
        configs.each {it.tearDown()}
    }

    public void addConfig(Config config){
        configs.add(config)
    }

    public void removeConfig(Config config){
        configs.remove(config)
    }
}
