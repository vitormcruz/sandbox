package sandbox.magritte.validationGenerator

/**
 */
class ParameterAccessor extends Accessor{

    private paramNumber

    void setParamNumber(paramNumber) {
        this.paramNumber = paramNumber
    }

    @Override
    def getClojureValue() {
        return {return delegate.args[paramNumber]}
    }
}
