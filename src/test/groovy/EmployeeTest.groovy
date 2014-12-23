import org.junit.Test
import org.junit.runner.RunWith
import sandbox.magritte.Description
import sandbox.magritte.DescriptionMethod
import sandbox.magritte.StringDescription
import sandbox.payroll.Employee
import sandbox.testGenerator.junit.JUnit4TestGeneratorRunner
import sandbox.testGenerator.magritte.model.TestDescriptionContainer

@RunWith(JUnit4TestGeneratorRunner)
class EmployeeTest {

    @Test
    public void "ahaaa!!!!"(){
        assert true : "bloblo"
    }

    @DescriptionMethod
    def Description myDescription(){
        return  new TestDescriptionContainer(getEmployeeClass(), new StringDescription().acessor("name").maxSize(100))
    }

    Class<Employee> getEmployeeClass() {
        return Employee
    }
}
