package sandbox.magritte.validationGenerator.methodGenerator.imp
import org.apache.commons.lang.StringUtils
import org.apache.commons.validator.routines.CodeValidator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.validator.Validation
import sandbox.validator.imp.ValidatorTrait

class MaxSizeValidationMethod extends SimpleGeneratedMethod implements ValidatorTrait{

    private Object accessor
    private Object maxSize

    MaxSizeValidationMethod() {
    }

    MaxSizeValidationMethod(accessor, maxSize) {
        this.accessor = accessor
        this.maxSize = maxSize
        validateFailingOnError()

        createValidationMethod(accessor, maxSize)
    }

    @Validation
    def void validateAccessor(){
        if(StringUtils.isBlank(accessor)) throw new IllegalArgumentException("MaxSizeValidationMethod.creation.accessor.required")
    }

    @Validation
    def void validateMaxSize(){
        if(maxSize == null) throw new IllegalArgumentException("MaxSizeValidationMethod.creation.maxSize.required")
        if(maxSize < 0) throw new IllegalArgumentException("MaxSizeValidationMethod.creation.maxSize.negative")
    }

    private void createValidationMethod(accessor, maxSize) {
        super.methodName = "Validate ${accessor} has no more than ${maxSize} characters."
        super.clojure = {
            def value = delegate."${accessor}"
            if (!new CodeValidator("", 0, maxSize, null).isValid(value)) {
                throw new IllegalArgumentException("${delegate.getClass().getSimpleName().toLowerCase()}.validation.${accessor}.maxsize.error")
            }
        }
    }
}
