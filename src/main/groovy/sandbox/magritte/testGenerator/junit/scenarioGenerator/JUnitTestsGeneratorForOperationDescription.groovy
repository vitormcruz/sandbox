package sandbox.magritte.testGenerator.junit.scenarioGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.description.NewOperationDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.testGenerator.MandatoryTestGeneratorForMethod

class JUnitTestsGeneratorForOperationDescription implements MethodGenerator, NewOperationDescription {

    private ValidationFactory validationFactory = new ValidationFactory()
    private Object describedClass
    private String methodName
    private Collection<GeneratedMethod> generatedTests = []
    MandatoryTestGeneratorForMethod mandatoryTestGenerator = MandatoryTestGeneratorForMethod.smartNewFor(JUnitTestsGeneratorForOperationDescription);
    def validation

    JUnitTestsGeneratorForOperationDescription(def describedClass) {
        this.describedClass = describedClass
        mandatoryTestGenerator.setClassUnderTest(describedClass)
    }

    @Override
    NewOperationDescription method(String methodName, Description... descriptions) {
        this.methodName = methodName
        mandatoryTestGenerator.setMethodUnderTest(methodName)
        this.validation = validationFactory.getValidationMethodFor(methodName, describedClass)
        mandatoryTestGenerator.setValidationMethod(validation)
        descriptions.each { description ->
            generatedTests.addAll(description.asTestGenerator(describedClass, mandatoryTestGenerator, validation).getGeneratedMethods())
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
