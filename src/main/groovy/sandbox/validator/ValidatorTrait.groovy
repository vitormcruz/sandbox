package sandbox.validator
import org.junit.runner.Result
import org.junit.runner.notification.RunListener

/**
 */
trait ValidatorTrait {

    def ResultInterface validate(){
        //TODO copied from JUnitCore, probably will change in function of specific listeners or notifiers from different layers (presentation, persistence etc) interested on validation result, but I don't know how yet.
        Result result = new Result();
        RunListener listener = result.createListener();
        ValidatorGlobals.notifier.addFirstListener(listener);
        def validatorRunner = new ValidatorRunner(this)
        try {
            ValidatorGlobals.notifier.fireTestRunStarted(validatorRunner.getDescription());
            validatorRunner.run(ValidatorGlobals.notifier);
            ValidatorGlobals.notifier.fireTestRunFinished(result);
        } finally {
            ValidatorGlobals.notifier.removeListener(listener);
        }

        return result as ResultInterface;
    }
}
