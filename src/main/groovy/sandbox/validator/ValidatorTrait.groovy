package sandbox.validator
import org.junit.runner.JUnitCore
import org.junit.runner.RunWith

/**
 */
@RunWith(ValidatorRunner)
trait ValidatorTrait {

    def ResultInterface validate(){
        return JUnitCore.runClasses(this.getClass()) as ResultInterface
    }
}
