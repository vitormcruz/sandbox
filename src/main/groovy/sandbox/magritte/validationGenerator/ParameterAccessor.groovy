package sandbox.magritte.validationGenerator

/**
 */
class ParameterAccessor extends Accessor{

    private paramValue

    void setParamValue(paramValue) {
        this.paramValue = paramValue
    }

    def getValue(){
        return paramValue
    }

}
