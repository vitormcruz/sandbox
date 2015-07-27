package sandbox.magritte.validationGenerator.methodGenerator.imp

import sandbox.magritte.validationGenerator.Accessor
import sandbox.magritte.validationGenerator.GeneratedValidationMethod
import sandbox.validator.imp.ValidatorTrait

/**
 */
abstract class BasicGeneratedValidationMethod extends GeneratedValidationMethod implements ValidatorTrait{

    def newForAccessor(Accessor accessor){
            this.addValidation("Validate accessor is not null", {
                if(accessor == null) throw new IllegalArgumentException("sandbox.magritte.validationgenerator.methodgenerator.imp.BasicValidationMethod.createValidationMethod.validation.accessor.mandatory.error")
            }).validateFailingOnError()

        methodName = defineName(accessor.name)
        methodBody = defineClosure(accessor)
        return this
    }

    abstract String defineName(String accessorName)
    abstract Closure defineClosure(Accessor accessor)


}
