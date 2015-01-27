package sandbox.magritte.validationGenerator.description
import sandbox.magritte.description.Description
import sandbox.magritte.description.DescriptionContainer
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.validationGenerator.ValidationGeneratorForDescriptionContainer
import sandbox.magritte.validationGenerator.ValidationGeneratorForStringDescription

class ValidationGenerationDescriptionExtensions {

    public static MethodGenerator asMethodGenerator(Description aDescription){
        def validationGenerator = aDescription.getMyValidationGenerator()
        return aDescription.accept(validationGenerator)
    }

    public static MethodGenerator getMyValidationGenerator(DescriptionContainer aDescription){
        return new ValidationGeneratorForDescriptionContainer()
    }

    public static MethodGenerator getMyValidationGenerator(StringDescription aDescription){
        return new ValidationGeneratorForStringDescription()
    }
}
