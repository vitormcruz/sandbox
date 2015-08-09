package sandbox.magritte.validationGenerator.descriptionModel
import sandbox.magritte.description.Description
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator

class ValidationGeneratorForOperationDescription implements MethodGenerator, OperationDescription{

    private ValidationForOperation generatedMethod

    ValidationGeneratorForOperationDescription() {
        generatedMethod = new ValidationForOperation()
    }

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return [generatedMethod]
    }

    @Override
    OperationDescription named(String operationName) {
        generatedMethod.setOperationName(operationName)
        return null
    }

    @Override
    OperationDescription forConstructor() {
        named("newInstance")
        return this
    }

    @Override
    OperationDescription withParameter(number, name, Description description) {
        generatedMethod.setValidation(number, name, description)
        return this
    }
}
