package sandbox.magritte.validationGenerator.descriptionModel

import sandbox.magritte.description.NumberDescription
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.validationGenerator.ValidationFactory
import sandbox.magritte.validationGenerator.validations.DefaultValidationFactory

class ValidationGeneratorForNumberDescription extends ValidationGeneratorForBaseDescription
                                              implements MethodGenerator, NumberDescription{

    ValidationFactory validationFactory = DefaultValidationFactory.smartNew(ValidationGeneratorForNumberDescription)

    @Override
    NumberDescription beNatural() {
        addValidation(validationFactory.getBeNaturalValidation(accessor))
        return this
    }
}
