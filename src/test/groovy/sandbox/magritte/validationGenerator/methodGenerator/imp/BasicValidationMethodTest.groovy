package sandbox.magritte.validationGenerator.methodGenerator.imp
import org.junit.Test
import sandbox.magritte.validationGenerator.Accessor
import sandbox.validator.ResultInterface
import sandbox.validator.imp.ValidationException

import static groovy.test.GroovyAssert.shouldFail
import static org.hamcrest.CoreMatchers.hasItem
import static org.junit.Assert.assertThat
/**
 */
abstract class BasicValidationMethodTest{

    @Test
    def void "accessor is required"(){
        ValidationException ex = shouldFail(ValidationException, {getValidationMethodWith(null)})
        assertThat(extractErrorMessagesFromResult(ex.result),
                   hasItem("sandbox.magritte.validationgenerator.methodgenerator.imp.BasicValidationMethod.createValidationMethod.validation.accessor.mandatory.error"))
    }

    protected List<String> extractErrorMessagesFromResult(ResultInterface result) {
        result.getFailures().collect { it.getException().getMessage() }
    }

    abstract def getValidationMethodWith(Accessor accessor)
}
