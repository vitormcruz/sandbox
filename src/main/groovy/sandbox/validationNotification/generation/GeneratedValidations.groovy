package sandbox.validationNotification.generation

import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder

class GeneratedValidations {

    private Map<String,Closure> methodValidations = [:]
    private Object subjectOfValidation

    GeneratedValidations(subjectOfValidation){
        methodValidations.putAll(MagritteDescriptionModelBuilder.myInstance.forObject(subjectOfValidation).getGeneratedValidations())
    }

    public validate(methodName, args){
        def validation = methodValidations.get(methodName)
        if (validation) {
            validation(*args)
        }
    }
}
