package sandbox.magritte.validationGenerator.validations

import sandbox.validatorJunit.ValidatorTrait
/**
 */
abstract class BasicGeneratedValidationMethod extends GeneratedValidationMethod implements ValidatorTrait{

    def newForAccessor(Accessor accessor){
            this.withValidation("Validate accessor is not null", {
                if(accessor == null) throw new IllegalArgumentException("sandbox.magritte.validationgenerator.methodgenerator.imp.BasicValidationMethod.createValidationMethod.validation.accessor.mandatory.error")
            }).validateFailingOnError()

        methodName = defineName(accessor.name)
        methodBody = defineClosure(accessor)
        return this
    }

    abstract String defineName(String accessorName)
    abstract Closure defineClosure(Accessor accessor)


}
