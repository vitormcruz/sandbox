package sandbox.payroll

import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.Ignore
import org.junit.Test

/**
 * I test the equals and hashcode of identifiable objects. By default I test the IdentifiableTrait, but you can
 * override to provide another class for testing through the getClassForTest and getRedefinedChangeStateClass method.
 */
@Ignore("Incomplete")
class EqualsAndHashCodeTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(getClassForTest()).withOnlyTheseFields("id")
                      .withRedefinedSubclass(getRedefinedChangeStateClass())
                      .verify();
    }

    public Class getClassForTest() {
        IdentifiableClassForTest
    }

    /**
     * Must subclass the class returned by getClassForTest with added state
     */
    public Class getRedefinedChangeStateClass(){
        return IdentifiableClassWithAddedStateForTest
    }

    public static class IdentifiableClassForTest implements IdentifiableTrait{
        @Override
        boolean canEqual(Object other) {
            return other instanceof IdentifiableClassForTest
        }
    }

    public static class IdentifiableClassWithAddedStateForTest extends IdentifiableClassForTest {

        //Let's pretend this class added state.
        @Override
        boolean canEqual(Object other) {
            return other instanceof IdentifiableClassWithAddedStateForTest
        }
    }
}
