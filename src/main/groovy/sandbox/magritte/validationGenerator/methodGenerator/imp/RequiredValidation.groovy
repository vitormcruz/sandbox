package sandbox.magritte.validationGenerator.methodGenerator.imp

import sandbox.magritte.validationGenerator.Accessor

/**
 */
class RequiredValidation extends BasicValidationMethod {

    @Override
    String defineName(String accessorName) {
        return "Validate if ${accessorName} is provided"
    }

    @Override
    Closure defineClosure(Accessor accessor) {
        return {
            if (accessor.getValue(delegate) == null) {
                throw new IllegalArgumentException("${delegate.getClass().getName().toLowerCase()}.validation.${accessor.name}.mandatory.error")
            }
        }
    }
}
