package sandbox.magritte.validationGenerator

import sandbox.magritte.validationGenerator.methodGenerator.imp.MaxSizeValidationMethod
import sandbox.magritte.validationGenerator.methodGenerator.imp.RequiredValidation

class DefaultValidationFactory<T> implements ValidationFactory<T>{

    DefaultValidationFactory() {
    }

    @Override
    def T getMaxSizeValidation(accessor, maxSize){
        return new MaxSizeValidationMethod(accessor, maxSize);
    }

    @Override
    T getRequiredValidation(Object accessor) {
        return new RequiredValidation(accessor);
    }
}
