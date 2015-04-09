package sandbox.magritte.validationGenerator.methodGenerator.imp

import org.apache.commons.validator.routines.CodeValidator
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.validator.MustBeValidToo
import sandbox.validator.imp.ValidatorTrait

import static sandbox.magritte.description.builder.DescriptionFactory.New

class MaxSizeValidationMethod extends SimpleGeneratedMethod implements ValidatorTrait{

    MaxSizeValidationMethod() {
    }

    MaxSizeValidationMethod(accessor, maxSize) {
        new ConstructorValidation(this, accessor, maxSize).validateFailingOnError()
        createValidationMethod(accessor, maxSize)
    }

    private void createValidationMethod(accessor, maxSize) {
        super.methodName = "Validate ${accessor} has no more than ${maxSize} characters."
        super.closure = {
            def value = delegate."${accessor}"
            if (!new CodeValidator("", 0, maxSize, null).isValid(value)) {
                throw new IllegalArgumentException("${delegate.getClass().getName().toLowerCase()}.validation.${accessor}.maxsize.error")
            }
        }
    }

    public static class ConstructorValidation implements ValidatorTrait{

        @MustBeValidToo
        private Object myObjectAlmostConstructed
        private Object accessor
        private Object maxSize

        ConstructorValidation(myObjectAlmostConstructed, accessor, maxSize) {
            this.maxSize = maxSize
            this.accessor = accessor
            this.myObjectAlmostConstructed = myObjectAlmostConstructed
        }

        @DescriptionModelDefinition
        public myDescription(){
            return [New(StringDescription).accessor("accessor").beNotBlank(),
                    New(NumberDescription).accessor("maxSize").beRequired().beNatural()]
        }
    }


}
