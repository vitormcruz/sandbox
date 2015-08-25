package sandbox.magritte.validationGenerator.validations

import org.junit.Test
import org.junit.runner.RunWith
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.description.TestDescription
import sandbox.magritte.testGenerator.junit.JUnit4TestGeneratorRunner
import sandbox.validator.imp.ValidationException

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat
import static sandbox.magritte.description.OperationDescription.FIRST
import static sandbox.magritte.description.builder.DescriptionFactory.New

@RunWith(JUnit4TestGeneratorRunner)
class MaxSizeValidationMethodTest extends BasicValidationMethodTest{

    public static final Accessor tstAccessor = new Accessor(name: "tst")

    @DescriptionModelDefinition
    public myDescription(){
        return New(TestDescription)
                .descriptionsFor(MaxSizeValidationMethod,
                                 New(OperationDescription).forConstructor()
                                                          .withParameter(FIRST, "maxSize",
                                                                         New(NumberDescription).beRequired()))
    }

    //TODO implement generation for these tests
    @Test
    def void "maxSize must be a natural number"(){
        def errorMatcher = hasItem("sandbox.magritte.validationgenerator.validations.maxsizevalidationmethod.validation.maxSize.natural.number.error")
        def successMatcher = not(hasItem("sandbox.magritte.validationgenerator.validations.maxsizevalidationmethod.validation.maxSize.natural.number.error"))

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
