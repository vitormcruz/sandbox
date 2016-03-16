package sandbox.magritte.testGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.description.TestDescription

class TestGeneratorForTestDescription implements TestDescription, MethodGenerator {

    private TestContext testContext
    private Description[] descriptions

    @Override
    def TestGeneratorForTestDescription descriptionsFor(Class classUnderTest, Description... descriptions) {
        this.descriptions = descriptions
        testContext = new TestContext(classUnderTest).withMandatoryTestGenerator(MandatoryTestGeneratorForMethod.smartNewFor(TestGeneratorForTestDescription))
        return this
    }

    @Override
    TestDescription usingThisValidationMethod(Closure validationMethod) {
        testContext = testContext.withValidationMethod(validationMethod)
        return this
    }

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return descriptions.collectMany { it.asTestGenerator(testContext).getGeneratedMethods() }
    }
}
