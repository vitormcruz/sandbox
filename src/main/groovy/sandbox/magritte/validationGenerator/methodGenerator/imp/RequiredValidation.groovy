package sandbox.magritte.validationGenerator.methodGenerator.imp

import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

/**
 */
class RequiredValidation extends SimpleGeneratedMethod {

    def RequiredValidation(def accessor) {
        super.methodName = "Validate if ${accessor} is provided"
        super.closure = {
            def value = delegate."${accessor}"
            if (value == null) {
                throw new IllegalArgumentException("${delegate.getClass().getName().toLowerCase()}.validation.${accessor}.mandatory.error")
            }
        }
    }
}
