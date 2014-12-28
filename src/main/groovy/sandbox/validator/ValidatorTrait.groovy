package sandbox.validator
import org.junit.runner.Result
import org.junit.runner.RunWith
import org.junit.runner.notification.RunListener

import static sandbox.validator.ValidatorGlobals.notifier

/**
 */
@RunWith(ValidatorRunner)
trait ValidatorTrait {

    def ResultInterface validate(){
        //TODO copied from JUnitCore, probably will change in function of specific listeners or notifiers from different layers (presentation, persistence etc) interested on validation result, but I don't know how yet.
        Result result = new Result();
        RunListener listener = result.createListener();
        notifier.addFirstListener(listener);
        def validatorRunner = new ValidatorRunner(this)
        try {
            notifier.fireTestRunStarted(validatorRunner.getDescription());
            validatorRunner.run(notifier);
            notifier.fireTestRunFinished(result);
        } finally {
            notifier.removeListener(listener);
        }

        return result as ResultInterface;
    }
}
