package sandbox.magritte.validationGenerator.descriptionModel

import sandbox.magritte.description.ObjectDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.MethodGenerator
import sandbox.magritte.validationGenerator.ValidationFactory
import sandbox.magritte.validationGenerator.validations.Accessor
import sandbox.magritte.validationGenerator.validations.DefaultValidationFactory
import sandbox.magritte.validationGenerator.validations.InstanceAccessor

abstract class ValidationGeneratorForBaseDescription implements MethodGenerator, ObjectDescription{

    //TODO Substitute for validation factory interface
    protected ValidationFactory validationFactory = DefaultValidationFactory.smartNewFor(ValidationGeneratorForBaseDescription)
    protected Accessor accessor = new InstanceAccessor()
    private validations = []

    ValidationGeneratorForBaseDescription(describedObject) {
        accessor = new InstanceAccessor(describedObject)
    }

    @Override
    ObjectDescription accessor(String accessorName) {
        accessor.name = accessorName
        return this
    }

    void setAccessor(Accessor accessor) {
        this.accessor = accessor
    }

    @Override
    ObjectDescription beRequired() {
        addValidation(validationFactory.getRequiredValidation(accessor))
        return this
    }

    @Override
    ObjectDescription defaultValue(Object defaultValue) {
        return this
    }

    @Override
    ObjectDescription label(Object label) {
        return this
    }

    def void addValidation(GeneratedMethod generatedValidation){
        validations.add(generatedValidation)
    }

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return new ArrayList(validations)
    }

}
