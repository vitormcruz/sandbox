package sandbox.magritte.validationGenerator

import sandbox.magritte.description.NumberDescription
import sandbox.magritte.methodGenerator.description.MethodGenerator

class ValidationGeneratorForNumberDescription extends ValidationGeneratorForBaseDescription
                                              implements MethodGenerator, NumberDescription{

    ValidationFactory validationFactory = new DefaultValidationFactory()

    @Override
    NumberDescription beNatural() {
        addValidation(validationFactory.getBeNaturalValidation(accessor))
        return this
    }
}
