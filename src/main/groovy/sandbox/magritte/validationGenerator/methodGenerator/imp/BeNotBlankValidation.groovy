package sandbox.magritte.validationGenerator.methodGenerator.imp
import org.apache.commons.lang.StringUtils
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.validator.MustBeValidToo
import sandbox.validator.imp.ValidatorTrait

import static sandbox.magritte.description.builder.DescriptionFactory.New

class BeNotBlankValidation extends SimpleGeneratedMethod implements ValidatorTrait{


    public static final int MIN_SIZE = 1

    BeNotBlankValidation(accessor) {
        new ConstructorValidation(this, accessor).validateFailingOnError()
        createValidationMethod(accessor)
    }

    private void createValidationMethod(accessor) {
        super.methodName = "Validate ${accessor} cannot be blank."
        super.closure = {
            def value = delegate."${accessor}"
            if (StringUtils.isBlank(value)) {
                throw new IllegalArgumentException("${delegate.getClass().getName().toLowerCase()}.validation.${accessor}.mandatory.error")
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
