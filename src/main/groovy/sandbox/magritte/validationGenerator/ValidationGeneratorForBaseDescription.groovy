package sandbox.magritte.validationGenerator
import sandbox.magritte.description.BaseDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator

abstract class ValidationGeneratorForBaseDescription implements MethodGenerator, BaseDescription{

    ValidationFactory validationFactory = new DefaultValidationFactory<>()
    def protected Accessor accessor = new Accessor()

    private validations = []

    @Override
    BaseDescription accessor(String accessorName) {
        accessor.name = accessorName
        return this
    }

    void setAccessor(Accessor accessor) {
        this.accessor = accessor
    }

    @Override
    BaseDescription beRequired() {
        addValidation(validationFactory.getRequiredValidation(accessor))
        return this
    }

    @Override
    BaseDescription defaultValue(Object defaultValue) {
        return this
    }

    @Override
    BaseDescription label(Object label) {
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
