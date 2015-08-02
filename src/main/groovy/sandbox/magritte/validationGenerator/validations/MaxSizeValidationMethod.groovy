package sandbox.magritte.validationGenerator.validations
import org.apache.commons.validator.routines.CodeValidator
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.OperationDescription

import static sandbox.magritte.description.OperationDescription.FIRST
import static sandbox.magritte.description.builder.DescriptionFactory.New

class MaxSizeValidationMethod extends BasicGeneratedValidationMethod {

    private CodeValidator codeValidator

    MaxSizeValidationMethod() {
    }

    MaxSizeValidationMethod(maxSize) {
        createCodeValidator(maxSize)
    }

    void createCodeValidator(maxSize) {
        this.validateFailingOnErrorFor("createCodeValidator", maxSize)
        codeValidator = new CodeValidator("", 0, maxSize, null)
    }

    @Override
    String defineName(String accessorName) {
        return "Validate ${accessorName} has no more than ${codeValidator.getMaxLength()} characters."
    }

    @Override
    Closure defineClosure(Accessor accessor) {
        return {
            accessor.setDelegate(delegate)
            if (!codeValidator.isValid(accessor.getValue())) {
                throw new IllegalArgumentException("${delegate.getClass().getName().toLowerCase()}.validation.${accessor.name}.maxsize.error")
            }
        }
    }

    @DescriptionModelDefinition
    public myDescription(){
        return [New(OperationDescription).named("createCodeValidator")
                                         .withParameter(FIRST, "maxSize", New(NumberDescription).beRequired()
                                                                                                .beNatural())]
    }
}
