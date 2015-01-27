package sandbox.validator.imp

import org.junit.runner.notification.RunNotifier
import sandbox.validator.ParentValidatorRunner
import sandbox.validator.ValidatorTrait
/**
 */
trait DefaultValidatorTrait implements ValidatorTrait{

    def private validatorRunner = new ValidatorRunner(this)
    def private runNotifier = new RunNotifier()

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
