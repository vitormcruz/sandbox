package sandbox.magritte.validationGenerator.descriptionModel
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.validationGenerator.ValidationFactory
import sandbox.magritte.validationGenerator.validations.DefaultValidationFactory

class ValidationGeneratorForStringDescription extends ValidationGeneratorForBaseDescription
                                              implements MethodGenerator, StringDescription{

    ValidationFactory validationFactory = new DefaultValidationFactory()

    @Override
    StringDescription beNotBlank() {
        addValidation(validationFactory.getBeNotBlankValidation(accessor))
        return this
    }

    @Override
    StringDescription maxSize(Integer maxSize) {
        addValidation(validationFactory.getMaxSizeValidation(accessor, maxSize))
        return this
    }
}
