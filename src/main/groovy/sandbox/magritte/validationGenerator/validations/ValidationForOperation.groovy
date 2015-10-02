package sandbox.magritte.validationGenerator.validations
import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.GeneratedMethod
/**
 */
class ValidationForOperation extends GeneratedValidationMethod{

    def private String operationName
    def private Collection<GeneratedMethod> validations = []
    private Map<Integer, ParameterAccessor> parametersMap = [:]

    void setOperationName(String operationName){
        this.operationName = operationName
        this.methodName = "validationFor_$operationName"
    }

    def getValidations() {
        return validations
    }

    def setValidation(Integer paramNumber, String paramName, Description description){
        def accessor = getParameterAccessorFor(paramNumber, paramName)
        def validation = description.asMethodGenerator(null, accessor)
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

            parametersMap.values().each {
                it.setArguments([paramsContext].flatten())
            }

            getValidations().each {
                it.getMethodBody().setDelegate(grasshopper)
                grasshopper.withValidation(it.getMethodName(), it.getMethodBody(), operationName)
            }
        }
    }
}
