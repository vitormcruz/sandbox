package sandbox.heavyValidation
import org.joda.time.DateTime
import org.junit.runner.Description
import org.junit.runner.Result
import sandbox.validationNotification.ValidationObserver
/**
 */
public class TextRunListener implements ValidationObserver {

    def DateTime testStartTime
    def String objectUnderTest
    private Boolean successful

    void testRunStarted(Description description) throws Exception {
        objectUnderTest = description.displayName
        println("Validation of ${objectUnderTest} initiated")
    }

    void testRunFinished(Result result) throws Exception {
        println("Validation of ${objectUnderTest} was ${result.wasSuccessful() ? "": "not "}sucecessful")
    }

    @Override
    void startValidation(String validationName) {
        println("Validation of ${validationName} initiated")
        testStartTime = new DateTime()
    }

    @Override
    void issueMandatoryObligation(String mandatoryValidationName, String error) {
        println(/Mandatory obligation registered for ${mandatoryValidationName} with error:  "$error"/)
    }

    @Override
    void issueMandatoryObligationComplied(String mandatoryValidationName) {
        println(/Mandatory obligation complied for ${mandatoryValidationName} /)
    }

    @Override
    void finishValidation(String validationName) {
        def timeElapsed = new DateTime().getMillis() - testStartTime.getMillis()
        println("Validation of ${validationName} finished. Elapsed time: ${timeElapsed} mls")
    }

    @Override
    Boolean successful() {
        return successful
    }

    @Override
    void issueError(String error) {
        successful = false
        println(error)
    }

}

