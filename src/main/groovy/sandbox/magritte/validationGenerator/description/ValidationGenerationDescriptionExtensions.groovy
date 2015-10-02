package sandbox.magritte.validationGenerator.description

import sandbox.magritte.description.Description
import sandbox.magritte.description.NumberDescription
import sandbox.magritte.description.OperationDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.validationGenerator.descriptionModel.ValidationGeneratorCollection
import sandbox.magritte.validationGenerator.descriptionModel.ValidationGeneratorForNumberDescription
import sandbox.magritte.validationGenerator.descriptionModel.ValidationGeneratorForOperationDescription
import sandbox.magritte.validationGenerator.descriptionModel.ValidationGeneratorForStringDescription
import sandbox.magritte.validationGenerator.validations.Accessor

class ValidationGenerationDescriptionExtensions {

    public static MethodGenerator asMethodGenerator(Description aDescription, Object describedObject, Accessor accessor){
        def validationGenerator = aDescription.getMyValidationGenerator(describedObject)
        validationGenerator.setAccessor(accessor)
        return aDescription.playbackAt(validationGenerator)
    }

    public static MethodGenerator asMethodGenerator(Description aDescription, Object describedObject){
        def validationGenerator = aDescription.getMyValidationGenerator(describedObject)
        return aDescription.playbackAt(validationGenerator)
    }

    public static MethodGenerator asMethodGenerator(Collection descriptions, Object describedObject){
        return new ValidationGeneratorCollection(descriptions, describedObject)
    }

    public static MethodGenerator getMyValidationGenerator(StringDescription aDescription, Object describedObject){
        return new ValidationGeneratorForStringDescription(describedObject)
    }

    public static MethodGenerator getMyValidationGenerator(NumberDescription aDescription, Object describedObject){
        return new ValidationGeneratorForNumberDescription(describedObject)
    }

    public static MethodGenerator getMyValidationGenerator(OperationDescription aDescription, Object describedObject){
        return new ValidationGeneratorForOperationDescription()
    }
}
