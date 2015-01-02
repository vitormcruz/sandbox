package sandbox.validator

import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunListener

/**
 * Result interface for JUnit result. I will use groovy  to make Result to be seen as a ResultInterface
 */
interface ResultInterface {

    public RunListener createListener();

    /**
     * @return the number of tests run
     */
    public int getRunCount();

    /**
     * @return the number of tests that failed during the run
     */
    public int getFailureCount();

    /**
     * @return the number of milliseconds it took to run the entire suite to run
     */
    public long getRunTime();

    /**
     * @return the {@link Failure}s describing tests that failed and the problems they encountered
     */
    public List<Failure> getFailures();

    /**
     * @return the number of tests ignored during the run
     */
    public int getIgnoreCount();

    /**
     * @return <code>true</code> if all tests succeeded
     */
    public boolean wasSuccessful();
}
