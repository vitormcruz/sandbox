package sandbox.magritte.testGenerator.junit.scenarioGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.description.NewOperationDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.testGenerator.MandatoryTestGeneratorForMethod
import sandbox.magritte.testGenerator.TestContext

class JUnitOperationDescriptionTestGenerator implements MethodGenerator, NewOperationDescription {

    private ValidationFactory validationFactory = new ValidationFactory()
    private Object describedClass
    private String methodName
    private Collection<GeneratedMethod> generatedTests = []
    MandatoryTestGeneratorForMethod mandatoryTestGenerator = MandatoryTestGeneratorForMethod.smartNewFor(JUnitOperationDescriptionTestGenerator);
    def validation
    private TestContext testContext

    JUnitOperationDescriptionTestGenerator(TestContext testContext) {
        this.testContext = testContext
    }

    @Override
    NewOperationDescription method(String methodName, Description... descriptions) {
        descriptions.collectMany { it.asTestGenerator().getScenarios()}


        this.methodName = methodName
        testContext = testContext.withMethodUnderTest(methodName)
        mandatoryTestGenerator.setTestContext(testContext)
        descriptions.each { description ->
            def testGenerator = description.asTestGenerator(describedClass, validation)
            testGenerator.setMandatoryTestGenerator(mandatoryTestGenerator)
            generatedTests.addAll(testGenerator.getGeneratedMethods())
        }
        return this
    }

    @Override
    NewOperationDescription forConstructor() {
        named("newInstance")
        return this
    }

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return [generatedTests, mandatoryTestGenerator.getGeneratedMethods()].flatten()
    }
}
