package sandbox.magritte.validationGenerator.methodGenerator.imp

import org.junit.Test
import sandbox.validator.ResultInterface
import sandbox.validator.ValidationException

import static groovy.test.GroovyAssert.shouldFail
import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat

class MaxSizeValidationMethodTest {

    @Test
    def void "accessor is required"(){
        ValidationException ex = shouldFail(ValidationException, {new MaxSizeValidationMethod(null, 10)})
        assertThat(extractErrorMessagesFromResult(ex.result),
                   hasItem("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.createValidationMethod.validation.accessor.mandatory.error"))
    }

    @Test
    def void "accessor cannot be empty"(){
        ValidationException ex = shouldFail(ValidationException, {new MaxSizeValidationMethod("", 10)})
        assertThat(extractErrorMessagesFromResult(ex.result),
                   hasItem("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.createValidationMethod.validation.accessor.mandatory.error"))
    }

    @Test
    def void "maxSize is required"(){
        ValidationException ex = shouldFail(ValidationException, {new MaxSizeValidationMethod("tst", null)})
        assertThat(extractErrorMessagesFromResult(ex.result),
                   hasItem("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.createValidationMethod.validation.maxSize.mandatory.error"))
    }

    @Test
    def void "maxSize must be a natural number"(){
        def errorMatcher = hasItem("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.createValidationMethod.validation.maxSize.natural.number.error")
        def successMatcher = not(hasItem("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.createValidationMethod.validation.maxSize.natural.number.error"))

        [[maxSize: -10, expected: errorMatcher],
         [maxSize: -1, expected: errorMatcher],
         [maxSize: 0, expected: successMatcher],
         [maxSize:10, expected: successMatcher]].each { example ->
            def errors = captureErrors {new MaxSizeValidationMethod("tst", example.maxSize)}
            assertThat(errors, example.expected)
        }
    }

    def Collection captureErrors(clojure){
        try{
            clojure()
        }catch (ValidationException ve){
            return extractErrorMessagesFromResult(ve.result)
        }

        return []
    }

    private List<String> extractErrorMessagesFromResult(ResultInterface result) {
        result.getFailures().collect { it.getException().getMessage() }
    }

}
