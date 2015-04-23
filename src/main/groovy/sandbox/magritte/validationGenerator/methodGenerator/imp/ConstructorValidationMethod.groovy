package sandbox.magritte.validationGenerator.methodGenerator.imp

import sandbox.magritte.description.Description
import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

class ConstructorValidationMethod extends SimpleGeneratedMethod {

    private Collection<Parameter> parameters = []
    private Collection<String> parameterNames = []

    def OperationValidationMethodBuilder withParameter(Object number, Object name, Description description) {
        parameters.add(new Parameter(number, name, description))
        parameterNames.add(name)
        return this
    }

    @Override
    void teachMyselfTo(Class grasshopper) {
        parameterNames.each {paramName -> grasshopper.metaClass."parameter_${paramName}_ForConstructor"}
        grasshopper.getConstructor()
        super.teachMyselfTo(grasshopper)

    }

    static class OperationValidationMethodBuilder{


        def ConstructorValidationMethod build(){

        }

    }
}
