package sandbox.payroll

import org.junit.Test
import org.junit.runner.RunWith
import sandbox.magritte.description.DescriptionMethod
import sandbox.magritte.description.StringDescription
import sandbox.magritte.testGenerator.junit.JUnit4TestGeneratorRunner
import sandbox.magritte.testGenerator.description.TestDescription

import static sandbox.magritte.description.DescriptionFactory.New

@RunWith(JUnit4TestGeneratorRunner)
class EmployeeTest {

    @Test
    public void "ahaaa!!!!"(){
        assert true : "bloblo"
    }

    @DescriptionMethod
    def myDescription(){
        return New(TestDescription).descriptionsFor(getEmployeeClass(),
                                                    New(StringDescription).acessor("name").maxSize(100))
    }

    Class<Employee> getEmployeeClass() {
        return Employee
    }
}