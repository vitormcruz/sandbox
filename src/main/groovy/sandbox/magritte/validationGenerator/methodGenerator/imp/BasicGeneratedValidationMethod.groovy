package sandbox.magritte.validationGenerator.methodGenerator.imp

import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.magritte.validationGenerator.Accessor
import sandbox.validator.imp.ValidatorTrait

/**
 */
abstract class BasicGeneratedValidationMethod extends SimpleGeneratedMethod implements ValidatorTrait{

    def newForAccessor(Accessor accessor){
        forClassification("newForAccessor")
            .addValidation("Validate accessor is not null",{
                if(accessor == null) throw new IllegalArgumentException("sandbox.magritte.validationgenerator.methodgenerator.imp.BasicValidationMethod.createValidationMethod.validation.accessor.mandatory.error")
            }).validateFailingOnError()

        super.methodName = defineName(accessor.name)
        super.methodBody = defineClosure(accessor)
        return this
    }

    abstract String defineName(String accessorName)
    abstract Closure defineClosure(Accessor accessor)


}
