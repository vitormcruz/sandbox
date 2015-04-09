package sandbox.magritte.validationGenerator.methodGenerator.imp

import org.apache.commons.validator.routines.IntegerValidator
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.validator.MustBeValidToo
import sandbox.validator.imp.ValidatorTrait

import static sandbox.magritte.description.builder.DescriptionFactory.New

class BeNaturalValidation extends SimpleGeneratedMethod implements ValidatorTrait{

    static def naturalNumberValidator = new IntegerValidator()
    public static final int MIN_NATURAL_INTEGER = 0

    BeNaturalValidation(accessor) {
        new ConstructorValidation(this, accessor).validateFailingOnError()
        createValidationMethod(accessor)
    }

    private void createValidationMethod(accessor) {
        super.methodName = "Validate ${accessor} is a natural number, i.e., only positives, zero included, integer numbers."
        super.closure = {
            def value = delegate."${accessor}"
            if (!(value instanceof Integer) || !naturalNumberValidator.minValue(value, MIN_NATURAL_INTEGER)) {
                throw new IllegalArgumentException("${delegate.getClass().getName().toLowerCase()}.validation.${accessor}.natural.number.error")
            }
        }
    }

    public static class ConstructorValidation implements ValidatorTrait {

        @MustBeValidToo
        private Object myObjectAlmostConstructed
        private Object accessor

        ConstructorValidation(myObjectAlmostConstructed, accessor) {
            this.accessor = accessor
            this.myObjectAlmostConstructed = myObjectAlmostConstructed
        }

        @DescriptionModelDefinition
        public myDescription(){
            return [New(StringDescription).accessor("accessor").beRequired()]
        }
    }
}
