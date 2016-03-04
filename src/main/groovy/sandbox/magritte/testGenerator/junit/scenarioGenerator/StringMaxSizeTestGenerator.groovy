package sandbox.magritte.testGenerator.junit.scenarioGenerator
import org.apache.commons.lang.StringUtils
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat

class StringMaxSizeTestGenerator implements MethodGenerator{

    private String label
    private int maxSize
    private Object describedClass
    private Collection<MethodGenerator> testMethods
    private cachedGeneratedMethods = {return generateMethods()}
    private Closure validationMethod

    StringMaxSizeTestGenerator(String label, Integer maxSize, Class describedClass, Closure validationMethod) {
        this.validationMethod = validationMethod
        this.describedClass = describedClass
        this.maxSize = maxSize
        this.label = label
    }

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return cachedGeneratedMethods()
    }

    def generateMethods(){
        testMethods = []

        String error = "${describedClass.getName().toLowerCase()}.validation.${label}.maxsize.error"
        //TODO the implementation of the actual method should be specific of the test framework

        def errorMatcher = hasItem(error)
        def successMatcher = not(hasItem(error))
        [[size: maxSize -1, testVerificationMatcher: successMatcher],
         [size: maxSize, testVerificationMatcher: successMatcher],
         [size: maxSize + 1, testVerificationMatcher: errorMatcher]].each {

            testMethods.add(new SimpleGeneratedMethod("The ${label} of ${describedClass.getSimpleName()} should have " +
                    "at max ${maxSize} characters. Testing with ${it.size} characters.",
                    testSizeTemplate(it.size, it.testVerificationMatcher)))

        }

        cachedGeneratedMethods = {return testMethods}
        return testMethods
    }

    def testSizeTemplate(size, testVerificationMatcher){
        return {
            def result = validate(size)
            assertThat(result, testVerificationMatcher)
        }

    }

    def private validate(size) {
        def valueToTest = StringUtils.leftPad("", size, 'X')
        return validationMethod(valueToTest)
    }
}
