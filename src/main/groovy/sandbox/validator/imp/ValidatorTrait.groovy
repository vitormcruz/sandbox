package sandbox.validator.imp

import org.junit.runner.notification.RunNotifier
import sandbox.validator.AbstractValidatorTrait
import sandbox.validator.ParentValidatorRunner

/**
 */
trait ValidatorTrait implements AbstractValidatorTrait{

    def private validatorRunner = new ValidatorRunner(this)
    def private runNotifier = new RunNotifier()

    public ParentValidatorRunner getValidatorRunner() {
        return validatorRunner
    }

    public RunNotifier getNotifier() {
        return runNotifier
    }
}
