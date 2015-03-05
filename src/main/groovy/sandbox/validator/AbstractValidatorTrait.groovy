package sandbox.validator
import org.junit.runner.Result
import org.junit.runner.notification.RunListener
import org.junit.runner.notification.RunNotifier

trait AbstractValidatorTrait {

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

    public abstract ParentValidatorRunner getValidatorRunner()
    public abstract RunNotifier getNotifier()
}
