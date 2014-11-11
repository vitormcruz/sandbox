import magritte.Description
import magritte.DescriptionMethod
import magritte.StringDescription
import magritte.test.TestSuitDescription
import magritte.test.junit.JUnit4MagritteDescriptionAwareRunner
import org.junit.Test
import org.junit.runner.RunWith
import sandbox.Employee

@RunWith(JUnit4MagritteDescriptionAwareRunner)
class EmployeeTest {

    @Test
    public void "ahaaa!!!!"(){
        assert true : "bloblo"
    }

    @DescriptionMethod
    def Description myDescription(){
        def testSuitDescription = TestSuitDescription().forClass(getEmployeeClass())
        testSuitDescription.addRestrictionDefinition(new StringDescription().acessor("name").beRequired())
        return testSuitDescription
    }

    Class<Employee> getEmployeeClass() {
        return Employee
    }
}
