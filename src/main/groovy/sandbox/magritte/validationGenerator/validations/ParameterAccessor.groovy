package sandbox.magritte.validationGenerator.validations
/**
 */
class ParameterAccessor implements Accessor{

    private paramName
    private paramNumber
    private ThreadLocal<Collection> arguments = new ThreadLocal<Collection>()

    void setParamNumber(paramNumber) {
        this.paramNumber = paramNumber
    }

    void setArguments(Collection arguments) {
        this.arguments.set(arguments)
    }

    @Override
    String getName() {
        return paramName
    }

    void setName(paramName) {
        this.paramName = paramName
    }

    @Override
    def getValue() {
        return arguments.get().get(paramNumber-1)
    }
}
