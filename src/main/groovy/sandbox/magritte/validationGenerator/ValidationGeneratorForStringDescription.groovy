package sandbox.magritte.validationGenerator
import sandbox.magritte.description.Description
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator

class ValidationGeneratorForStringDescription implements MethodGenerator, StringDescription{

    ValidationFactory validationFactory = new DefaultValidationFactory<>()

    def private validations = []
    //TODO create a wrapper class for this.
    def private accessorProxy = [:]

    @Override
    StringDescription maxSize(Integer maxSize) {
        validations.add(validationFactory.getMaxSizeValidation(accessorProxy.acessor, maxSize))
        return this
    }

    @Override
    Description accessor(String accessor) {
        accessorProxy.put("acessor", accessor)
        return this
    }

    @Override
    Description beRequired() {
        validations.add(validationFactory.getRequiredValidation(accessorProxy.acessor))
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

    @Override
    Collection<GeneratedMethod> getGeneratedMethods() {
        return new ArrayList(validations)
    }
}
