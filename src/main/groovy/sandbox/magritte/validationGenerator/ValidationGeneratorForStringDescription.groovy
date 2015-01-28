package sandbox.magritte.validationGenerator

import sandbox.magritte.description.Description
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

class ValidationGeneratorForStringDescription implements MethodGenerator, StringDescription{

    ValidationFactory validationFactory = new DefaultValidationFactory<SimpleGeneratedMethod>(SimpleGeneratedMethod)

    def private validations = []
    def private accessorProxy = [:]

    @Override
    StringDescription maxSize(Integer maxSize) {
        validations.add(validationFactory.getMaxSizeValidation(accessorProxy.acessor, maxSize))
        return this
    }

    private SimpleGeneratedMethod teste(accessor, maxSize) {
    }

    @Override
    Description accessor(String accessor) {
        accessorProxy.put("acessor", accessor)
        return this
    }

    @Override
    Description beRequired() {
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
