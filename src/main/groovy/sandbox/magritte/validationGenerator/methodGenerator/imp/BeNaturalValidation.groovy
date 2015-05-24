package sandbox.magritte.validationGenerator.methodGenerator.imp
import org.apache.commons.validator.routines.IntegerValidator
import sandbox.magritte.validationGenerator.Accessor

class BeNaturalValidation extends BasicValidationMethod{

    static def naturalNumberValidator = new IntegerValidator()
    public static final int MIN_NATURAL_INTEGER = 0

    @Override
    String defineName(String accessorName) {
        return "Validate ${accessorName} is a natural number, i.e., only positives, zero included, integer numbers."
    }

    @Override
    Closure defineClosure(Accessor accessor) {
        return {
            accessor.setDelegate(delegate)
            def value = accessor.getValue()
            if (!(value instanceof Integer) || !naturalNumberValidator.minValue(value, MIN_NATURAL_INTEGER)) {
                throw new IllegalArgumentException("${delegate.getClass().getName().toLowerCase()}.validation.${accessor.name}.natural.number.error")
            }
        }
    }
}
