package sandbox.smartfactory

import org.junit.Test

class SmartFactoryTest {

    @Test
    def void "get instance of class not configured must return null"(){
        assert new SmartFactory().instanceForCallerOf(SmartFactoryTest, String) == null
    }

    @Test
    def void "get instance of class configured should return the configured instance"(){
        def smartFactory = new SmartFactory()
        smartFactory.configurationFor("sandbox.smartfactory.SmartFactoryTest").put(String, "ok")
        assert smartFactory.instanceForCallerOf(SmartFactoryTest, String) == "ok"
    }
}
