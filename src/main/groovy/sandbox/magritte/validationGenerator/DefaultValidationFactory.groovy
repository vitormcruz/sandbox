package sandbox.magritte.validationGenerator

import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.validationGenerator.methodGenerator.imp.BeNaturalValidation
import sandbox.magritte.validationGenerator.methodGenerator.imp.BeNotBlankValidation
import sandbox.magritte.validationGenerator.methodGenerator.imp.MaxSizeValidationMethod
import sandbox.magritte.validationGenerator.methodGenerator.imp.RequiredValidation

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
