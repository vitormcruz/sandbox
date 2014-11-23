import sandbox.magritte.Description
import sandbox.magritte.DescriptionMethod
import sandbox.magritte.StringDescription
import org.junit.Test
import org.junit.runner.RunWith
import sandbox.payroll.Employee
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
