package sandbox.magritte.validationGenerator.validations
import org.apache.commons.validator.routines.CodeValidator

class MaxSizeValidationMethod extends BasicGeneratedValidationMethod {

    private CodeValidator codeValidator

    MaxSizeValidationMethod() {
    }

    MaxSizeValidationMethod(maxSize) {
        codeValidator = new CodeValidator("", 0, maxSize, null)
    }

    @Override
    String defineName(String accessorName) {
        return "Validate ${accessorName} has no more than ${codeValidator.getMaxLength()} characters."
    }

    @Override
    Closure defineClosure(Accessor accessor) {
        return {
            if (!codeValidator.isValid(accessor.getValue())) {
                throw new IllegalArgumentException("${delegate.getClass().getName().toLowerCase()}.validation.${accessor.name}.maxsize.error")
            }
        }
    }

}
