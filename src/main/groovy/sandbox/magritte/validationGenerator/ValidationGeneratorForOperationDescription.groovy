package sandbox.magritte.validationGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

class ValidationGeneratorForOperationDescription implements MethodGenerator, OperationDescription{

    private String operationName
    def validations = []

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return validations
    }

    @Override
    OperationDescription named(String operationName) {
        this.operationName = operationName
        return null
    }

    @Override
    OperationDescription forConstructor() {
        named("constructor")
        return this
    }

    @Override
    OperationDescription withParameter(number, name, Description description) {

        validations.add(new SimpleGeneratedMethod(methodName: "Teste", closure:{

            println("Passei por aqui!!!!")

        }))

        return this
    }
}
