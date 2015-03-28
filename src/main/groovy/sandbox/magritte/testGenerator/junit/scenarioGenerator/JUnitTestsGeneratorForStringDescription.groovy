package sandbox.magritte.testGenerator.junit.scenarioGenerator
import org.apache.commons.lang.StringUtils
import sandbox.magritte.description.Description
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

    Collection<GeneratedMethod> getGeneratedMethods(){
        return super.testScenarios
    }

    @Override
    StringDescription maxSize(Integer maxSize) {
        String error = "${describedClass.getSimpleName().toLowerCase()}.validation.${accessor}.maxsize.error"

        //TODO the implementation of the actual method should be specific of the test framework
        def errorMatcher = hasItem(error)
        def successMatcher = not(hasItem(error))
        [[size: maxSize -1, testVerificationMatcher: sucessMatcher],
         [size: maxSize, testVerificationMatcher: sucessMatcher],
         [size: maxSize + 1, testVerificationMatcher: errorMatcher]].each {

            testScenarios.add(new SimpleGeneratedMethod("The ${accessor} of ${describedClass.getSimpleName()} should have " +
                                               "at max ${maxSize} characters. Testing with ${it.size} characters.",
                                                testSizeTemplate(it.size, it.testVerificationMatcher)))

        }


        return this
    }

    def testSizeTemplate(size, testVerificationMatcher){
        return {
           def testSubject = describedClass.newInstance()
            testSubject."${accessor}" = StringUtils.leftPad("", size, 'X')
            def result = testSubject.validate()

            assertThat(result.getFailures().collect {it.getException().getMessage()}, testVerificationMatcher)
        }

    }

    @Override
    Description defaultValue(Object defaultValue) {
        return this
    }

    @Override
    Description label(Object label) {
        return this
    }
}
