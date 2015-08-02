package sandbox.magritte.validationGenerator.validations

import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.validationGenerator.ValidationFactory

class DefaultValidationFactory implements ValidationFactory{

    DefaultValidationFactory() {
    }

    @Override
    def GeneratedMethod getMaxSizeValidation(Accessor accessor, maxSize){
        return new MaxSizeValidationMethod(maxSize).newForAccessor(accessor);
    }

    @Override
    GeneratedMethod getRequiredValidation(Accessor accessor) {
        return new RequiredValidation().newForAccessor(accessor);
    }

    @Override
    GeneratedMethod getBeNaturalValidation(Accessor accessor) {
        return new BeNaturalValidation().newForAccessor(accessor);
    }

    @Override
    GeneratedMethod getBeNotBlankValidation(Accessor accessor) {
        return new BeNotBlankValidation().newForAccessor(accessor)
    }
}
