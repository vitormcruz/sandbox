package sandbox.magritte.validationGenerator.validations
/**
 */
class ParameterAccessor extends Accessor{

    private paramNumber
    private ThreadLocal<Collection> arguments = new ThreadLocal<Collection>()

    void setParamNumber(paramNumber) {
        this.paramNumber = paramNumber
    }

    void setArguments(Collection arguments) {
        this.arguments.set(arguments)
    }

    @Override
    def getValue() {
        return arguments.get().get(paramNumber-1)
    }
}
