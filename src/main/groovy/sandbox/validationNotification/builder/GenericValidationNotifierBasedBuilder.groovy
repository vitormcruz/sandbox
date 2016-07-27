package sandbox.validationNotification.builder

import sandbox.validationNotification.ApplicationValidationNotifier

abstract class GenericValidationNotifierBasedBuilder {

    protected builderObserver = new BuilderObserver()
    private Map messagesToBuildObject = [:]

    GenericValidationNotifierBasedBuilder() {
        ApplicationValidationNotifier.addObserver(this.builderObserver)
    }

    def methodMissing(String name, def args) {
        messagesToBuildObject.put(name, args)
    }

    /**
     * Build the object and execute aSuccessClosure upon success. Return the built object or null in case of failure.
     */
    public buildAndDoOnSuccess(aSuccessClosure){
        return builderObserver.buildAndDo(aSuccessClosure, {})
    }

    /**
     * Build the object and execute aSuccessClosure upon success or aFailureClosure otherwise. Return the built object
     * or null in case of failure.
     */
    public buildAndDo(aSuccessClosure, aFailureClosure){
        return builderObserver.buildAndDo(aSuccessClosure, aFailureClosure)
    }

    /**
     *
     * @return
     */
    public builtEntity(){
        return builderObserver.buildAndDo({}, {})
    }

}
