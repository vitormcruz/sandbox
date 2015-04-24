package sandbox.magritte.validationGenerator.methodGenerator.imp
import org.apache.commons.validator.routines.CodeValidator
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.validationGenerator.Accessor

import static sandbox.magritte.description.OperationDescription.FIRST
import static sandbox.magritte.description.builder.DescriptionFactory.New

class MaxSizeValidationMethod extends BasicValidationMethod {

    private CodeValidator codeValidator

    MaxSizeValidationMethod(maxSize) {
        createCodeValidator(maxSize)
    }

    void createCodeValidator(maxSize) {
        forMethod("createCodeValidator").withLooseValidation("Validate maxSize is not null",
                                            {
                                               if(maxSize==null) throw new IllegalArgumentException("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.createValidationMethod.validation.maxSize.mandatory.error")
                                            })
                     .withLooseValidation("Validate maxSize is a natural number",
                                           {
                                              if(maxSize<0) throw new IllegalArgumentException("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.createValidationMethod.validation.maxSize.natural.number.error")
                                           })
                     .validateFailingOnError()
        codeValidator = new CodeValidator("", 0, maxSize, null)
    }

    @Override
    String defineName(String accessorName) {
        return "Validate ${accessorName} has no more than ${codeValidator.getMaxLength()} characters."
    }

    @Override
    Closure defineClosure(Accessor accessor) {
        return {
            if (!codeValidator.isValid(accessor.getValue(delegate))) {
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
