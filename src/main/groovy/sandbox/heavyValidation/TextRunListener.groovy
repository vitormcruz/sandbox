package sandbox.heavyValidation

import org.joda.time.DateTime
import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunListener

/**
 */
public class TextRunListener extends RunListener{

    def DateTime testStartTime
    def String objectUnderTest

    @Override
    void testRunStarted(Description description) throws Exception {
        objectUnderTest = description.displayName
        println("Validation of ${objectUnderTest} initiated")
    }

    @Override
    void testRunFinished(Result result) throws Exception {
        println("Validation of ${objectUnderTest} was ${result.wasSuccessful() ? "": "not "}sucecessful")
    }

    @Override
    void testStarted(Description description) throws Exception {
        println("Validation of ${description.displayName} initiated")
        testStartTime = new DateTime()
    }

    @Override
    void testFinished(Description description) throws Exception {
        def timeElapsed = new DateTime().getMillis() - testStartTime.getMillis()
        println("Validation of ${description.displayName} finished. Elapsed time: ${timeElapsed} mls")
    }

    @Override
    void testFailure(Failure failure) throws Exception {
        super.testFailure(failure)
    }

    @Override
    void testAssumptionFailure(Failure failure) {
        super.testAssumptionFailure(failure)
    }

    @Override
    void testIgnored(Description description) throws Exception {
        super.testIgnored(description)
    }
}

