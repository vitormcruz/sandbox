import org.junit.Test
import org.junit.runner.RunWith
import sandbox.magritte.Description
import sandbox.magritte.DescriptionContainer
import sandbox.magritte.DescriptionMethod
import sandbox.magritte.StringDescription
import sandbox.payroll.Employee
import sandbox.testGenerator.junit.JUnit4TestGeneratorRunner

@RunWith(JUnit4TestGeneratorRunner)
class EmployeeTest {

    @Test
    public void "ahaaa!!!!"(){
        assert true : "bloblo"
    }

    @DescriptionMethod
    def Description myDescription(){
        return  new DescriptionContainer(getEmployeeClass(), new StringDescription().acessor("name").maxSize(100))
    }

    Class<Employee> getEmployeeClass() {
        return Employee
    }
}
