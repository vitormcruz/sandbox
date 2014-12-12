package sandbox.testGenerator.magritte
import org.apache.commons.lang.StringUtils
import sandbox.magritte.StringDescription
import sandbox.testGenerator.TestScenario

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.assertThat
//TODO extends Description? I must think more how I will leave the dependencies of descriptions....
class TestsGeneratorForStringDescription extends StringDescription {

    private Class descriptedClass
    private String acessor
    private testScenarios = []

    TestsGeneratorForStringDescription(Class descriptedClass) {
        this.descriptedClass = descriptedClass
    }

    def getTestScenarios(){
        return testScenarios
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

            testScenarios.add(new TestScenario("The ${acessor} of ${descriptedClass.getSimpleName()} should have " +
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

            assertThat(result, testVerificationMatcher)
        }

    }

    @Override
    StringDescription acessor(String acessor) {
        this.acessor = acessor
        return this
    }

    @Override
    StringDescription beRequired() {
        return null
    }

    @Override
    StringDescription defaultValue(Object defaultValue) {
        return null
    }

    @Override
    StringDescription label(Object label) {
        return null
    }
}
