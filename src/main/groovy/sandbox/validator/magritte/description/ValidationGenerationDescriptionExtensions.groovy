package sandbox.validator.magritte.description
import sandbox.magritte.description.Description
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.validator.validationGenerator.ValidationGeneratorForStringDescription

class ValidationGenerationDescriptionExtensions {

    public static MethodGenerator asMethodGenerator(Description aDescription){
        def validationGenerator = getValidationGenerator(aDescription)
        aDescription.accept(validationGenerator)
        return validationGenerator
    }

    public static MethodGenerator getValidationGenerator(StringDescription aDescription){
        return new ValidationGeneratorForStringDescription()
    }
}
