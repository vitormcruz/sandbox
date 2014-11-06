import magritte.Description
import magritte.DescriptionMethod
import magritte.StringDescription
import magritte.TestSuitDescription
import magritte.junit.JUnit4MagritteDescriptionAwareRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnit4MagritteDescriptionAwareRunner)
class EmployeeTest {

    @Test
    public void "ahaaa!!!!"(){
        assert true : "bloblo"
    }

    @DescriptionMethod
    def Description myDescription(){
        def testSuitDescription = new TestSuitDescription().forClass(getEmployeeClass())
        testSuitDescription.defineFieldAs("name", new StringDescription().beRequired())
        return testSuitDescription
    }

    Class<Employee> getEmployeeClass() {
        return Employee
    }
}
