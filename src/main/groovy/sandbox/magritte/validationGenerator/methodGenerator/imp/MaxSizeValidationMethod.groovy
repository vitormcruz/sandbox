package sandbox.magritte.validationGenerator.methodGenerator.imp

import org.apache.commons.lang.StringUtils
import org.apache.commons.validator.routines.CodeValidator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod
import sandbox.validator.imp.ValidatorTrait

class MaxSizeValidationMethod extends SimpleGeneratedMethod implements ValidatorTrait{

    MaxSizeValidationMethod() {
    }

    MaxSizeValidationMethod(accessor, maxSize) {
        createValidationMethod(accessor, maxSize)
    }

    void createValidationMethod(accessor, maxSize) {
        def result = forMethod("createValidationMethod")
                     .withLooseValidation("Validate accessor is not null",
                                         {
                                            if(StringUtils.isBlank(accessor)) throw new IllegalArgumentException("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.createValidationMethod.validation.accessor.mandatory.error")
                                         })
                     .withLooseValidation("Validate maxSize is not null",
                                            {
                                               if(maxSize==null) throw new IllegalArgumentException("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.createValidationMethod.validation.maxSize.mandatory.error")
                                            })
                     .withLooseValidation("Validate maxSize is a natural number",
                                           {
                                              if(maxSize<0) throw new IllegalArgumentException("sandbox.magritte.validationgenerator.methodgenerator.imp.maxsizevalidationmethod.createValidationMethod.validation.maxSize.natural.number.error")
                                           })
                     .validateFailingOnError()
        super.methodName = "Validate ${accessor} has no more than ${maxSize} characters."
        super.closure = {
            def value = delegate."${accessor}"
            if (!new CodeValidator("", 0, maxSize, null).isValid(value)) {
                throw new IllegalArgumentException("${delegate.getClass().getName().toLowerCase()}.validation.${accessor}.maxsize.error")
            }
        }
    }

//    @DescriptionModelDefinition
//    public myDescription(){
//        return [New(OperationDescription).forConstructor().withParameter(FIRST, "accessor", New(StringDescription).beNotBlank())
//                                                          .withParameter(SECOND, "maxSize", New(NumberDescription).beRequired()
//                                                                                                                   .beNatural())]
//    }
}
