package sandbox.magritte.testGenerator
import org.apache.commons.lang.StringUtils
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.magritte.testGenerator.junit.scenarioGenerator.TestMethodGenerator

import static org.hamcrest.CoreMatchers.hasItem
import static org.hamcrest.CoreMatchers.not
import static org.junit.Assert.assertThat

class MandatoryTestGeneratorForMethod implements TestMethodGenerator{
    private requiredParams = []
    private TestContext testContext

    void setTestContext(TestContext testContext) {
        this.testContext = testContext
    }

    def requiredAccessor(requiredParam) {
        this.requiredParams.add(requiredParam)
    }

    @Override
    Collection<GeneratedMethod> generateMethods(TestContext testContext) {
        if (requiredParams.isEmpty()) return Collections.emptyList()

        def protected testScenarios = []

        requiredParams.eachWithIndex { it, index ->

            def params = new Object[requiredParams.size()]
            String error = "${testContext.getTestedClassName().toLowerCase()}.validation.${it}.mandatory.error" //TODO should use the method name, but I will have to change validatin as well

            testScenarios.add(new SimpleGeneratedMethod("The ${it} parameter of ${testContext.getTestedClassSimpleName()}#${testContext.getMethodUnderTest()} is required. " +
                    "Testing providing null for it.",
                    {
                        def result = testContext.applyContextValidationTo(params)
                        assertThat(result, hasItem(error))
                    }))

        }


        testScenarios.add(new SimpleGeneratedMethod("Testing providing valid values to all required params of ${testContext.getTestedClassSimpleName()}#${testContext.getMethodUnderTest()}(${StringUtils.join(requiredParams, ", ")}) ",
                {
                    def result = testContext.applyContextValidationTo(requiredParams.collect{"X"}) //TODO how to get a valid value here?
                    requiredParams.each {
                        def error = "${testContext.getTestedClassName().toLowerCase()}.validation.${it}.mandatory.error"
                        assertThat(result, not(hasItem(error)))
                    }
                }))

        return testScenarios
    }

}
