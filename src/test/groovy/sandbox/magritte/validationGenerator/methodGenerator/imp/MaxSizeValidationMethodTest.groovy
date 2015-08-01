package sandbox.magritte.validationGenerator.methodGenerator.imp
import org.junit.Test
import sandbox.magritte.validationGenerator.Accessor
import sandbox.validator.imp.ValidationException

import static groovy.test.GroovyAssert.shouldFail
import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat

class MaxSizeValidationMethodTest extends BasicValidationMethodTest{

    public static final Accessor tstAccessor = new Accessor(name: "tst")

    @Test
    def void "maxSize is required"(){
        ValidationException ex = shouldFail(ValidationException, {new MaxSizeValidationMethod(null).newForAccessor(tstAccessor)})
        assertThat(extractErrorMessagesFromResult(ex.result),
                   hasItem("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.validation.maxSize.mandatory.error"))
    }

    @Test
    def void "maxSize must be a natural number"(){
        def errorMatcher = hasItem("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.validation.maxSize.natural.number.error")
        def successMatcher = not(hasItem("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.validation.maxSize.natural.number.error"))

        [[maxSize: -10, expected: errorMatcher],
         [maxSize: -1, expected: errorMatcher],
         [maxSize: 0, expected: successMatcher],
         [maxSize:10, expected: successMatcher]].each { example ->
            def errors = captureErrors {new MaxSizeValidationMethod(example.maxSize).newForAccessor(tstAccessor)}
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

    @Override
    def getValidationMethodWith(Accessor accessor) {
        return new MaxSizeValidationMethod(10).newForAccessor(accessor)
    }

}
