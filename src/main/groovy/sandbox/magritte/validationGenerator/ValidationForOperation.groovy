package sandbox.magritte.validationGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
/**
 */
class ValidationForOperation extends SimpleGeneratedMethod{

    def private Collection<GeneratedMethod> validations = []


    ValidationForOperation() {
        super.closure = {args ->
            getValidations().each {
                it.getClosure()
            }
        }
    }

    void setOperationName(String operationName){
        this.methodName = "validationFor_$operationName"
    }

    def getValidations() {
        return validations
    }

    def setValidation(Integer paramNumber, String paramName, Description description){
        def accessor = new ParameterAccessor(name: paramName, paramNumber: paramNumber)
        accessor.setDelegate(closure.getDelegate())
        def validation = description.getMyValidationGenerator()
        validation.setAccessor(accessor)
        description.accept(validation)
        validations.addAll(validation.getGeneratedMethods())
    }
}
