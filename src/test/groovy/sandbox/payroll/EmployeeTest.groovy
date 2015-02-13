package sandbox.payroll

import org.junit.Test
import org.junit.runner.RunWith
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.StringDescription
import sandbox.magritte.testGenerator.description.TestDescription
import sandbox.magritte.testGenerator.junit.JUnit4TestGeneratorRunner

import static sandbox.magritte.description.builder.DescriptionFactory.New

@RunWith(JUnit4TestGeneratorRunner)
class EmployeeTest {

    @Test
    public void "ahaaa!!!!"(){
        assert true : "bloblo"
    }

    @DescriptionModelDefinition
    def myTestDescription(){
        return New(TestDescription).descriptionsFor(getEmployeeClass(),
                                                    New(StringDescription).accessor("name").maxSize(50).beRequired(),
                                                    New(StringDescription).accessor("email").beRequired(),
                                                    New(StringDescription).accessor("address").maxSize(100).beRequired())
    }

    Class<Employee> getEmployeeClass() {
        return Employee
    }
}
