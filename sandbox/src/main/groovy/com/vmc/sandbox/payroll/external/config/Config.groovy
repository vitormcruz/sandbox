package com.vmc.sandbox.payroll.external.config

interface Config {

    /**
     * Make all the necessary configurations
     */
    public void configure()

    /**
     * Release all resources acquired in the configure method and does all the tear down needed. Must be executed in a
     * finally-like block.
     */
    public void tearDown()
}