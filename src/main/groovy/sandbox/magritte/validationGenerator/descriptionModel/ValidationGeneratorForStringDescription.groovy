package sandbox.magritte.validationGenerator.descriptionModel
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.description.MethodGenerator

class ValidationGeneratorForStringDescription extends ValidationGeneratorForBaseDescription
                                              implements MethodGenerator, StringDescription{

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
