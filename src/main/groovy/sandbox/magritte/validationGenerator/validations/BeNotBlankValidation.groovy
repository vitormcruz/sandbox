package sandbox.magritte.validationGenerator.validations
import org.apache.commons.lang.StringUtils

class BeNotBlankValidation extends BasicGeneratedValidationMethod{

    @Override
    String defineName(String accessorName) {
        return "Validate ${accessorName} cannot be blank."
    }

    @Override
    Closure defineClosure(Accessor accessor) {
        return {
            if (StringUtils.isBlank(accessor.getValue())) {
                throw new IllegalArgumentException("${delegate.getClass().getName().toLowerCase()}.validation.${accessor.name}.mandatory.error")
            }
        }
    }
}
