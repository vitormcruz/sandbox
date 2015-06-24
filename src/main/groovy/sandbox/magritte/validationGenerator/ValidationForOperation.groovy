package sandbox.magritte.validationGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.validator.LooseValidationBuilder

/**
 */
class ValidationForOperation extends SimpleGeneratedMethod{

    def private Collection<GeneratedMethod> validations = []
    private Map<Integer, ParameterAccessor> parametersMap = [:]


    ValidationForOperation() {
        super.closure = {args ->
            parametersMap.values().each {
                it.setArguments([args].flatten())
            }

            LooseValidationBuilder validationBuilder = delegate.classifying("ValidationForOperation")

            def outerDelegate = delegate
            getValidations().each {
                it.getClosure().setDelegate(outerDelegate)
                validationBuilder.addValidation(it.getMethodName(), it.getClosure())
            }
            validationBuilder.validateFailingOnError()
        }
    }

    void setOperationName(String operationName){
        this.methodName = "validationFor_$operationName"
    }

    def getValidations() {
        return validations
    }

    def setValidation(Integer paramNumber, String paramName, Description description){
        def accessor = getParameterAccessorFor(paramNumber, paramName)
        accessor.setDelegate(closure.getDelegate())
        def validation = description.getMyValidationGenerator()
        validation.setAccessor(accessor)
        description.accept(validation)
        validations.addAll(validation.getGeneratedMethods())
    }

    private ParameterAccessor getParameterAccessorFor(Integer paramNumber, String paramName) {
        def parameterAccessor = parametersMap.get(paramNumber)
        if( parameterAccessor == null){
            parameterAccessor = new ParameterAccessor(name: paramName, paramNumber: paramNumber)
            parametersMap.put(paramNumber, parameterAccessor)
        }

        return parameterAccessor
    }
}
