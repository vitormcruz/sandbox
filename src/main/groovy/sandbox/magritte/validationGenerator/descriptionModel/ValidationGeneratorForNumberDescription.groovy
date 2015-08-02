package sandbox.magritte.validationGenerator.descriptionModel
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.methodGenerator.description.MethodGenerator

class ValidationGeneratorForNumberDescription extends ValidationGeneratorForBaseDescription
                                              implements MethodGenerator, NumberDescription{

    @Override
    NumberDescription beNatural() {
        addValidation(validationFactory.getBeNaturalValidation(accessor))
        return this
    }
}
