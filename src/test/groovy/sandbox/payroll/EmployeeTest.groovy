package sandbox.payroll
import org.junit.Test
import org.junit.runner.RunWith
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.testGenerator.junit.JUnit4TestGeneratorRunner

@RunWith(JUnit4TestGeneratorRunner)
class EmployeeTest {

    @Test
    public void "ahaaa!!!!"(){
        assert true : "bloblo"
    }

    @DescriptionModelDefinition
    def myTestDescription(){
        return Employee.testsValidatesThat("name".isAString().maxSize(50).beRequired(),
                                           "address".isAString().maxSize(200).beRequired(),
                                           "email".isAString().maxSize(100).beRequired())
    }
}
