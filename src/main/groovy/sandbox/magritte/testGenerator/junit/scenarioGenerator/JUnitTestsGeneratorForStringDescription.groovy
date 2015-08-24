package sandbox.magritte.testGenerator.junit.scenarioGenerator
import org.apache.commons.lang.StringUtils
import sandbox.magritte.description.BaseDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat

class JUnitTestsGeneratorForStringDescription extends JunitTestGeneratorForBaseDescription implements StringDescription{

    JUnitTestsGeneratorForStringDescription(Class describedClass) {
        super.describedClass = describedClass
    }

    @Override
    BaseDescription accessor(String accessor) {
        super.accessor = accessor
        return this
    }
    

    @Override
    StringDescription beNotBlank() {
        return this
    }

    @Override
    StringDescription maxSize(Integer maxSize) {
        String error = "${describedClass.getName().toLowerCase()}.validation.${accessor}.maxsize.error"

        //TODO the implementation of the actual method should be specific of the test framework
        def errorMatcher = hasItem(error)
        def successMatcher = not(hasItem(error))
        [[size: maxSize -1, testVerificationMatcher: successMatcher],
         [size: maxSize, testVerificationMatcher: successMatcher],
         [size: maxSize + 1, testVerificationMatcher: errorMatcher]].each {

            testScenarios.add(new SimpleGeneratedMethod("The ${accessor} of ${describedClass.getSimpleName()} should have " +
                                               "at max ${maxSize} characters. Testing with ${it.size} characters.",
                                                testSizeTemplate(it.size, it.testVerificationMatcher)))

        }


        return this
    }

    def testSizeTemplate(size, testVerificationMatcher){
        return {
            def result = validate(size)
            assertThat(result, testVerificationMatcher)
        }

    }

    def private validate(size) {
        def valueToTest = StringUtils.leftPad("", size, 'X')
        if(validationMethod != null){
            return validationMethod(valueToTest)
        }

        def testSubject = describedClass.newInstance()
        testSubject."${accessor}" = valueToTest
        return testSubject.validate().getFailures().collect {it.getException().getMessage()}
    }

    @Override
    BaseDescription defaultValue(Object defaultValue) {
        return this
    }

    @Override
    BaseDescription label(Object label) {
        return this
    }
}
