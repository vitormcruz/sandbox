package sandbox.magritte.validationGenerator

import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

/**
 */
class GeneratedValidationMethod extends SimpleGeneratedMethod{

    @Override
    void teachMyselfTo(Object grasshopper) {
        getMethodBody().setDelegate(grasshopper)
        grasshopper.addValidation(getMethodName(), getMethodBody())
    }

    void teachMyselfToInContext(Object grasshopper, String context, paramsContext) {
        teachMyselfTo(grasshopper)
    }
}
