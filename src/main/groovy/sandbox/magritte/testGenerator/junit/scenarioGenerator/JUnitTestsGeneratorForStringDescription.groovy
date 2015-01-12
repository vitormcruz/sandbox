package sandbox.magritte.testGenerator.junit.scenarioGenerator
import org.apache.commons.lang.StringUtils
import sandbox.magritte.description.Description
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGeneration.GeneratedMethod
import sandbox.magritte.methodGeneration.imp.SimpleGeneratedMethod
import sandbox.magritte.methodGeneration.description.MethodGenerator

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat
class JUnitTestsGeneratorForStringDescription implements StringDescription, MethodGenerator {

    private Class descriptedClass
    private String acessor
    private testScenarios = []

    JUnitTestsGeneratorForStringDescription(Class descriptedClass) {
        this.descriptedClass = descriptedClass
    }

    Collection<GeneratedMethod> getGeneratedMethods(){
        return testScenarios
    }

    @Override
    Description acessor(String acessor) {
        this.acessor = acessor
        return this
    }

    @Override
    StringDescription maxSize(Integer maxSize) {
        String error = "${descriptedClass.getSimpleName().toLowerCase()}.validation.${acessor}.maxsize.error"

        //TODO the implementation of the actual method should be specific of the test framework
        def errorMatcher = hasItem(error)
        def sucessMatcher = not(hasItem(error))
        [[size: maxSize -1, testVerificationMatcher: sucessMatcher],
         [size: maxSize, testVerificationMatcher: sucessMatcher],
         [size: maxSize + 1, testVerificationMatcher: errorMatcher]].each {

            testScenarios.add(new SimpleGeneratedMethod("The ${acessor} of ${descriptedClass.getSimpleName()} should have " +
                                               "at max ${maxSize} characters. Testing with ${it.size} characters.",
                                                testSizeTemplate(it.size, it.testVerificationMatcher)))

        }


        return this
    }

    def testSizeTemplate(size, testVerificationMatcher){
        return {
           def testSubject = descriptedClass.newInstance()
            testSubject."${acessor}" = StringUtils.leftPad("", size, 'X')
            def result = testSubject.validate()

            assertThat(result.getFailures().collect {it.getException().getMessage()}, testVerificationMatcher)
        }

    }

    @Override
    Description beRequired() {
        return null
    }

    @Override
    Description defaultValue(Object defaultValue) {
        return null
    }

    @Override
    Description label(Object label) {
        return null
    }
}
