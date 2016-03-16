package sandbox.magritte.testGenerator.junit.scenarioGenerator
import org.apache.commons.lang.StringUtils
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.magritte.testGenerator.TestContext

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat

class StringMaxSizeTestGenerator implements TestMethodGenerator{

    private int maxSize

    StringMaxSizeTestGenerator(Integer maxSize) {
        this.maxSize = maxSize
    }

    @Override
    Collection<GeneratedMethod> generateMethods(TestContext testContext) {
        def testMethods = []

        String error = "${testContext.getTestedClassName().toLowerCase()}.validation.${testContext.getTestTargetName()}.maxsize.error"
        //TODO the implementation of the actual method should be specific of the test framework

        def errorMatcher = hasItem(error)
        def successMatcher = not(hasItem(error))
        [[size: maxSize -1, testVerificationMatcher: successMatcher],
         [size: maxSize, testVerificationMatcher: successMatcher],
         [size: maxSize + 1, testVerificationMatcher: errorMatcher]].each {

            testMethods.add(createMaxSizeMethodWith(it.size, it.testVerificationMatcher, testContext))

        }

        return testMethods
    }

    MethodGenerator createMaxSizeMethodWith(stringSize, testVerificationMatcher, TestContext testContext) {
        return new SimpleGeneratedMethod("The ${testContext.getTestTargetName()} of ${testContext.getTestedClassSimpleName()} should have " +
                                         "at max ${maxSize} characters. Testing with ${stringSize} characters.",
                                         testSizeTemplate(stringSize, testVerificationMatcher, testContext))
    }

    def testSizeTemplate(size, testVerificationMatcher, TestContext testContext){
        return {
            def result = testContext.applyContextValidationTo(StringUtils.leftPad("", size, 'X'))
            assertThat(result, testVerificationMatcher)
        }

    }
}
