package sandbox.validationNotification

import org.junit.Test
import sandbox.validationNotification.imp.MandatoryValidation

import static junit.framework.TestCase.fail

class MandatoryValidationTest implements ValidationNotificationTestSetup{

    @Test
    def void "Creating a mandatory validation issues a new mandatory obligation"(){
        new MandatoryValidation("teste", "expectedError")
        assert validationObserver.errors.contains("expectedError")
    }

    @Test
    def void "Setting null spam error"(){
        MandatoryValidation mandatoryValidation = new MandatoryValidation("teste", "expectedError")
        mandatoryValidation.set(null, {fail("Setting null using a mandatory validation should not issue the success closure")},
                                      {})

        assert validationObserver.errors.contains("expectedError")
    }

    @Test
    def void "Setting non null value should be successfull"(){
        MandatoryValidation mandatoryValidation = new MandatoryValidation("teste", "expectedError")
        mandatoryValidation.set("test", {},
                                        {fail("Setting null using a mandatory validation should not issue the fail closure")})
        assert validationObserver.errors.isEmpty()
    }
}
