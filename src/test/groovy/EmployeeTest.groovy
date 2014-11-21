import magritte.Description
import magritte.DescriptionMethod
import magritte.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import sandbox.Employee
import testGenerator.TestSuitDescription
import testGenerator.junit.JUnit4TestgeneratorRunner

@RunWith(JUnit4TestgeneratorRunner)
class EmployeeTest {

    @Test
    public void "ahaaa!!!!"(){
        assert true : "bloblo"
    }

    @DescriptionMethod
    def Description myDescription(){
        def testSuitDescription = TestSuitDescription().forClass(getEmployeeClass())
        testSuitDescription.addClassDefinition(new StringDescription().acessor("name").beRequired())
        return testSuitDescription
    }

    Class<Employee> getEmployeeClass() {
        return Employee
    }
}
