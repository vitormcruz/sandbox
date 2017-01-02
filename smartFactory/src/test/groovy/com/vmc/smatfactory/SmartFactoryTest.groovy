package com.vmc.smatfactory

import com.vmc.smartfactory.SmartFactory
import org.junit.Ignore
import org.junit.Test

class SmartFactoryTest {

    @Test
    def void "get instance of class not configured must return null"(){
        assert new SmartFactory().instanceForCallerOf(SmartFactoryTest, String) == null
    }

    @Test
    @Ignore
    def void "get instance of class configured should return the configured instance"(){
        def smartFactory = new SmartFactory()
        smartFactory.configurationFor("com.vmc.smartfactory.SmartFactoryTest").put(String, "ok")
        assert smartFactory.instanceForCallerOf(SmartFactoryTest, String) == "ok"
    }
}
