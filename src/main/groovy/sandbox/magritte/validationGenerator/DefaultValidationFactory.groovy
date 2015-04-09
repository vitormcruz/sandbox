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
    def GeneratedMethod getMaxSizeValidation(accessor, maxSize){
        return new MaxSizeValidationMethod(accessor, maxSize);
    }

    @Override
    GeneratedMethod getRequiredValidation(accessor) {
        return new RequiredValidation(accessor);
    }

    @Override
    GeneratedMethod getBeNaturalValidation(accessor) {
        return new BeNaturalValidation(accessor);
    }

    @Override
    GeneratedMethod getBeNotBlankValidation(accessor) {
        return new BeNotBlankValidation(accessor)
    }
}
