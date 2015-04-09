package sandbox.magritte.validationGenerator

import sandbox.magritte.description.BaseDescription
import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator

abstract class ValidationGeneratorForBaseDescription implements MethodGenerator, BaseDescription{

    ValidationFactory validationFactory = new DefaultValidationFactory<>()
    //TODO create a wrapper class for this.
    def protected accessorProxy = [:]

    private validations = []

    @Override
    Description accessor(String accessor) {
        accessorProxy.put("acessor", accessor)
        return this
    }

    @Override
    Description beRequired() {
        addValidation(validationFactory.getRequiredValidation(accessorProxy.acessor))
        return this
    }

    @Override
    Description defaultValue(Object defaultValue) {
        return this
    }

    @Override
    Description label(Object label) {
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
