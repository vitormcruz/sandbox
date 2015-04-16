package sandbox.magritte.validationGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

class ValidationGeneratorForOperationDescription implements MethodGenerator, OperationDescription{

    private String category
    def validations = []

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return validations
    }

    @Override
    OperationDescription named(String name) {
        return null
    }

    @Override
    OperationDescription forConstructor() {
        category = "constructor"
        return this
    }

    @Override
    OperationDescription withParameter(Object number, Object name, Description description) {

        validations.add(new SimpleGeneratedMethod(methodName: "Teste", {

            println("Passei por aqui!!!!")

        }))

        return this
    }
}
