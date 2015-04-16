package sandbox.magritte.validationGenerator.methodGenerator.imp
import org.apache.commons.validator.routines.CodeValidator
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.validator.imp.ValidatorTrait

import static sandbox.magritte.description.OperationDescription.FIRST
import static sandbox.magritte.description.OperationDescription.SECOND
import static sandbox.magritte.description.builder.DescriptionFactory.New

class MaxSizeValidationMethod extends SimpleGeneratedMethod implements ValidatorTrait{

    MaxSizeValidationMethod() {
    }

    MaxSizeValidationMethod(accessor, maxSize) {
        createValidationMethod(accessor, maxSize)
    }

    void createValidationMethod(accessor, maxSize) {
        super.methodName = "Validate ${accessor} has no more than ${maxSize} characters."
        super.closure = {
            def value = delegate."${accessor}"
            if (!new CodeValidator("", 0, maxSize, null).isValid(value)) {
                throw new IllegalArgumentException("${delegate.getClass().getName().toLowerCase()}.validation.${accessor}.maxsize.error")
            }
        }
    }

    @DescriptionModelDefinition
    public myDescription(){
        return [New(OperationDescription).forConstructor().withParameter(FIRST, "accessor", New(StringDescription).beNotBlank())
                                                          .withParameter(SECOND, "maxSize", New(NumberDescription).beRequired()
                                                                                                                   .beNatural())]
    }
}
