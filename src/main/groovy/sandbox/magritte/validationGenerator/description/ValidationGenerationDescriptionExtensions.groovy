package sandbox.magritte.validationGenerator.description

import sandbox.magritte.description.Description
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.validationGenerator.descriptionModel.ValidationGeneratorCollection
import sandbox.magritte.validationGenerator.descriptionModel.ValidationGeneratorForNumberDescription
import sandbox.magritte.validationGenerator.descriptionModel.ValidationGeneratorForOperationDescription
import sandbox.magritte.validationGenerator.descriptionModel.ValidationGeneratorForStringDescription

class ValidationGenerationDescriptionExtensions {

    public static MethodGenerator asMethodGenerator(Description aDescription){
        def validationGenerator = aDescription.getMyValidationGenerator()
        return aDescription.accept(validationGenerator)
    }

    public static MethodGenerator asMethodGenerator(Collection descriptions){
        return new ValidationGeneratorCollection(descriptions)
    }

    public static MethodGenerator getMyValidationGenerator(StringDescription aDescription){
        return new ValidationGeneratorForStringDescription()
    }

    public static MethodGenerator getMyValidationGenerator(NumberDescription aDescription){
        return new ValidationGeneratorForNumberDescription()
    }

    public static MethodGenerator getMyValidationGenerator(OperationDescription aDescription){
        return new ValidationGeneratorForOperationDescription()
    }
}
