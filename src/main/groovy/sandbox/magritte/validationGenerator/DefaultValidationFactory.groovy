package sandbox.magritte.validationGenerator
import org.apache.commons.lang.StringUtils

class DefaultValidationFactory<T> implements ValidationFactory<T>{

    Class<T> generatedMethodClass

    DefaultValidationFactory(Class<T> generatedMethodClass) {
        this.generatedMethodClass = generatedMethodClass
    }

    @Override
    def T getMaxSizeValidation(accessor, maxSize){
        generatedMethodClass.newInstance("Validate ${accessor} has no more than ${maxSize} characters.",
            {
                def value = delegate."${accessor}"
                if (StringUtils.length(value)> maxSize) {
                    throw new IllegalArgumentException("${delegate.getClass().getSimpleName().toLowerCase()}.validation.${accessor}.maxsize.error")
                }
            }
        )

    }
}
