package sandbox.validator.imp

import org.junit.runner.notification.RunNotifier
import sandbox.validator.AbstractValidatorTrait
import sandbox.validator.ParentValidatorRunner

/**
 */
trait ValidatorTrait implements AbstractValidatorTrait{

    def private runNotifier = new RunNotifier()

    public ParentValidatorRunner getValidatorRunner() {
        return new ValidatorRunner(this)
    }

    public RunNotifier getNotifier() {
        return runNotifier
    }
}
