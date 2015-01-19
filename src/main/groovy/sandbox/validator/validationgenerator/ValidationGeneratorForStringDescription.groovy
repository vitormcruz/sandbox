package sandbox.validator.validationGenerator

import sandbox.magritte.description.Description
import sandbox.magritte.description.StringDescription
import sandbox.magritte.methodGenerator.GeneratedMethod
import sandbox.magritte.methodGenerator.description.MethodGenerator
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

class ValidationGeneratorForStringDescription implements MethodGenerator, StringDescription{

    def private validations = []
    def private accessorProxy = [:]

    @Override
    StringDescription maxSize(Integer maxSize) {
        //TODO extract a common validation code that is tested and reused here to create dynamic validation
        validations.add(new SimpleGeneratedMethod("Validate ${accessorProxy.acessor} has no more than ${maxSize} characters.",
                {
                    def accessor = delegate."${accessorProxy.acessor}"
                    if(accessor != null && accessor.size() > maxSize){
                        throw new IllegalArgumentException("${delegate.getClass().getSimpleName().toLowerCase()}.validation.${accessorProxy.acessor}.maxsize.error")
                    }
                }
        ))
        return this
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
