package sandbox.magritte.validationGenerator.validations

import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

/**
 */
class GeneratedValidationMethod extends SimpleGeneratedMethod{

    @Override
    void teachMyselfTo(Object grasshopper) {
        getMethodBody().setDelegate(grasshopper) //TODO remove this. I woll have to apply the same alterations done with validations to the tests generation
        grasshopper.withValidation(getMethodName(), getMethodBody())
    }

    void teachMyselfToInContext(Object grasshopper, String context, paramsContext) {
        teachMyselfTo(grasshopper)
    }
}
