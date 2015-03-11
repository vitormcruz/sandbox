package sandbox.magritte.validationGenerator.methodGenerator.imp

import sandbox.magritte.methodGenerator.imp.SimpleGeneratedMethod

/**
 */
class RequiredValidation extends SimpleGeneratedMethod {

    def RequiredValidation(def accessor) {
        super.methodName = "Validate if ${accessor} is provided"
        super.clojure = {
            def value = delegate."${accessor}"
            if (value == null) {
                throw new IllegalArgumentException("${delegate.getClass().getSimpleName().toLowerCase()}.validation.${accessor}.mandatory.error")
            }
        }
    }
}
