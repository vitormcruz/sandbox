package sandbox.magritte.validationGenerator.validations
/**
 */
abstract class BasicGeneratedValidationMethod extends GeneratedValidationMethod {

    def newForAccessor(Accessor accessor){
        if(accessor == null) throw new IllegalArgumentException("sandbox.magritte.validationgenerator.methodgenerator.imp.BasicValidationMethod.createValidationMethod.validation.accessor.mandatory.error")
        methodName = defineName(accessor.name)
        methodBody = defineClosure(accessor)
        return this
    }

    abstract String defineName(String accessorName)
    abstract Closure defineClosure(Accessor accessor)


}
