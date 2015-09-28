package sandbox.validator.imp
import org.junit.Test
import org.junit.runner.Result
import org.junit.runner.notification.Failure
import sandbox.validator.ResultInterface

class ValidationExceptionTest {

    @Test
    def void "Test for no error"(){
        def ex = new ValidationException(getResultWith())

        assert ex.hasError("test.error") == false
    }


    @Test
    def void "Test for error that occurred"(){
        def ex = new ValidationException(getResultWith("test.error"))

        assert ex.hasError("test.error")
    }

    @Test
    def void "Test for error that occurred along with others"(){
        def ex = new ValidationException(getResultWith("test.error", "test.error2", "test.error3"))

        assert ex.hasError("test.error")
    }

    private ResultInterface getResultWith(String... errors) {
        def result = new Result() as ResultInterface
        def listener = result.createListener()
        errors.each {listener.testFailure(new Failure(null, new RuntimeException(it)))}
        return result
    }
}
