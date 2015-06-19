package sandbox.validator

import org.junit.runner.Result
import org.junit.runner.notification.RunListener
import org.junit.runner.notification.RunNotifier

trait AbstractValidatorTrait{

    private Map<String, Collection<String>> validationsForMethod = [:]

    def ResultInterface validate(){
        //TODO copied from JUnitCore, probably will change in function of specific listeners or notifiers from different layers (presentation, persistence etc) interested on validation result, but I don't know how yet.
        Result result = new Result();
        RunListener listener = result.createListener();
        getNotifier().addFirstListener(listener);
        try {
            getNotifier().fireTestRunStarted(getValidatorRunner().getDescription());
            getValidatorRunner().run(getNotifier());
            getNotifier().fireTestRunFinished(result);
        } finally {
            getNotifier().removeListener(listener);
        }

        return result as ResultInterface;
    }

    def void validateFailingOnError(){
        def result = validate()
        if(!result.wasSuccessful()) throw new ValidationException(result)
    }

    public abstract ParentValidatorRunner getValidatorRunner()
    public abstract RunNotifier getNotifier()

    def LooseValidationBuilder forMethod(String methodUnderValidation) {
        return new LooseValidationBuilder(this, methodUnderValidation)
    }

}
