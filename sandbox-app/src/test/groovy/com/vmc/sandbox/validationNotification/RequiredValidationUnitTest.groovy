package com.vmc.sandbox.validationNotification

import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import org.junit.Test
import com.vmc.sandbox.validationNotification.imp.RequiredValidation

import static junit.framework.TestCase.fail

class RequiredValidationUnitTest extends ValidationNotificationTestSetup{

    @Test
    def void "Creating a mandatory validation issues a new mandatory obligation"(){
        new RequiredValidation(this, [:], "teste", "expectedError")
        assert validationObserver.errors.contains("expectedError")
    }

    @Test
    def void "Setting null spam error"(){
        RequiredValidation mandatoryValidation = new RequiredValidation(this, [:], "teste", "expectedError")
        mandatoryValidation.set(null, {fail("Setting null using a mandatory validation should not issue the success closure")},
                                      {})

        assert validationObserver.errors.contains("expectedError")
    }

    @Test
    def void "Setting non null value should be successfull"(){
        RequiredValidation mandatoryValidation = new RequiredValidation(this, [:], "teste", "expectedError")
        mandatoryValidation.set("test", {},
                                        {fail("Setting null using a mandatory validation should not issue the fail closure")})
        assert validationObserver.errors.isEmpty()
    }
}
