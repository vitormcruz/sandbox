package sandbox.magritte.validationGenerator.descriptionModel
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.methodGenerator.MethodGenerator

class ValidationGeneratorForNumberDescription extends ValidationGeneratorForBaseDescription
                                              implements MethodGenerator, NumberDescription{

    ValidationGeneratorForNumberDescription(describedObject) {
        super(describedObject)
    }

    @Override
    NumberDescription beNatural() {
        addValidation(validationFactory.getBeNaturalValidation(accessor))
        return this
    }
}
