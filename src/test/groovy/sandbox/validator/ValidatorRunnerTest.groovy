package sandbox.validator
import org.junit.Before
import org.junit.Test
import org.junit.runner.Result
import org.junit.runner.notification.RunNotifier
import sandbox.validator.imp.ValidatorRunner

class ValidatorRunnerTest {
    private RunNotifier notifier

    @Before
    void setup(){
        notifier = new RunNotifier();
    }

    @Test
    def void "Validate with @Test"(){
        Result result = executeRunner(new ValidatorRunner(new ClassWithTest()))
        assert result.getRunCount() == 0 : "Should not run methods annotated with @Test."
    }

    public static class ClassWithTest{
        @Test
        def void "Should not run this method"(){
            assert true
        }
    }

    @Test
    def void "Validate should not fail initialization because the class do not have any validation"(){
        Result result = executeRunner(new ValidatorRunner(new ClassWithoutTestsOrValidations()))
        assert result.getFailureCount() == 0 : "Should not fail if a class do not have validation methods."
    }

    public static class ClassWithoutTestsOrValidations{}

    @Test
    def void "Validate with one method annotated with @Validation"(){
        Result result = executeRunner(new ValidatorRunner(new ClassWithOneValidationMethod()))
        assert result.getRunCount() == 1 : "Should run methods annotated with @Validation."
    }

    public static class ClassWithOneValidationMethod {
        @Validation
        def void "Should run this method"(){
            assert true
        }
    }

    @Test
    def void "Validate with N methods annotated with @Validation"(){
        Result result = executeRunner(new ValidatorRunner(new ClassWithNValidationMethods()))
        assert result.getRunCount() == 3 : "Should run methods annotated with @Validation."
    }

    @Test
    def void "Validate with N methods annotated with @Validation considering object state with validation success"(){
        def objectWithStateChange = new ClassWithNValidationMethods()
        objectWithStateChange.setExpectedAndActualResult(5, 5)
        Result result = executeRunner(new ValidatorRunner(objectWithStateChange))
        assert result.getRunCount() == 3 : "Should run methods annotated with @Validation."
        assert result.getFailureCount() == 0 : "All tests should pass."
    }

    @Test
    def void "Validate with N methods annotated with @Validation considering object state with validation failure"(){
        def objectWithStateChange = new ClassWithNValidationMethods()
        objectWithStateChange.setExpectedAndActualResult(5, 6)
        Result result = executeRunner(new ValidatorRunner(objectWithStateChange))
        assert result.getRunCount() == 3 : "Should run methods annotated with @Validation."
        assert result.getFailureCount() == 3 : "All tests should fail."
    }

    public static class ClassWithNValidationMethods {
        def actualResult
        def expected
        def executeAssertion = {assert false}

        void setExpectedAndActualResult(expected, actualResult) {
            this.actualResult = actualResult
            this.expected = expected
            executeAssertion = {assert expected == actualResult}
        }

        @Validation
        def void "Should run this method"(){
            executeAssertion()
        }

        @Validation
        def void "Should run this method too"(){
            executeAssertion()
        }

        @Validation
        def void "Should run this method also"(){
            executeAssertion()
        }
    }

    def executeRunner(validatorRunner){
        Result result = new Result();
        notifier.addListener(result.createListener())
        notifier.fireTestRunStarted(validatorRunner.getDescription());
        validatorRunner.run(notifier);
        notifier.fireTestRunFinished(result);
        return result
    }

    //TODO should use @Before, @After, @BeforeClass and #AfterClass????


}
