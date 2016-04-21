package sandbox.validation2

import sandbox.magritte.description.builder.MagritteDescriptionModelBuilder

class GeneratedValidations {

    private methodValidations = [:]

    GeneratedValidations(subjectOfValidation){
        MagritteDescriptionModelBuilder.myInstance.forObject(subjectOfValidation)
    }


    public validate(methodName, args){
        def validation = methodValidations.get(methodName)
        if (validation) {
            validation(*args)
        }
    }
}
