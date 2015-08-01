package sandbox.validator
import org.junit.Test

class DefaultAbstractValidatorTraitTest {

    @Test
    def void "Validate a class that implements ValidatorTrait"(){
        def result = new ClassUnderTest().validate()
        assert result.getFailureCount() == 1
    }

    public static class ClassUnderTest implements ValidatorTrait{
        @Validation
        public void validateSomething(){
            assert false
        }
    }
}
