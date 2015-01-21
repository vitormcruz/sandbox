package sandbox.validator.imp
import org.junit.runner.Result
import org.junit.runner.notification.RunListener
import org.junit.runner.notification.RunNotifier
import sandbox.validator.ParentValidatorRunner
import sandbox.validator.ResultInterface
import sandbox.validator.ValidatorTrait
/**
 */
trait DefaultValidatorTrait implements ValidatorTrait{

    def private validatorRunner = new ValidatorRunner(this)
    def private runNotifier = new RunNotifier()

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

    public ParentValidatorRunner getValidatorRunner() {
        return validatorRunner
    }

    public RunNotifier getNotifier() {
        return runNotifier
    }

    public void setValidatorRunner(ParentValidatorRunner runner) {
        this.validatorRunner = runner
    }

    public void setNotifier(RunNotifier runNotifier) {
        this.runNotifier = runNotifier
    }
}
