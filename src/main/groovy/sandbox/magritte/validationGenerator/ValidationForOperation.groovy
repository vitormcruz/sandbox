package sandbox.magritte.validationGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.GeneratedMethod
/**
 */
class ValidationForOperation extends GeneratedValidationMethod{

    def private String operationName
    def private Collection<GeneratedMethod> validations = []
    //TODO I think thread local must be here and not inside ParameterAccessor
    private Map<Integer, ParameterAccessor> parametersMap = [:]


    ValidationForOperation() {
        methodBody = {grasshopper, args ->
            parametersMap.values().each {
                it.setArguments([args].flatten())
            }

            setDelegate(grasshopper)
            getValidations().each {
                it.getMethodBody().setDelegate(grasshopper)
                delegate.addValidation(it.getMethodName(), it.getMethodBody(), operationName)
            }
        }
    }

    void setOperationName(String operationName){
        this.operationName = operationName
        this.methodName = "validationFor_$operationName"
    }

    def getValidations() {
        return validations
    }

    def setValidation(Integer paramNumber, String paramName, Description description){
        def accessor = getParameterAccessorFor(paramNumber, paramName)
        accessor.setDelegate(methodBody.getDelegate())
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

    @Override
    void teachMyselfTo(Object grasshopper) {
        //Do nothing. Operation validation should be teach only in the context of its operation.
    }

    @Override
    void teachMyselfToInContext(Object grasshopper, String context, paramsContext) {
        if(operationName.equals(context)){
            methodBody(grasshopper, paramsContext)
        }
    }
}
