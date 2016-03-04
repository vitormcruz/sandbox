package sandbox.payroll
import org.junit.Test
import org.junit.runner.RunWith
import sandbox.magritte.description.DescriptionModelDefinition
import sandbox.magritte.description.NewOperationDescription
import sandbox.magritte.description.StringDescription
import sandbox.magritte.description.TestDescription
import sandbox.magritte.testGenerator.junit.JUnit4TestGeneratorRunner

import static sandbox.magritte.description.builder.DescriptionFactory.New

@RunWith(JUnit4TestGeneratorRunner)
class EmployeeTest {

    @Test
    public void "ahaaa!!!!"(){
        assert true : "bloblo"
    }

//    @DescriptionModelDefinition
//    def myTestDescription(){
//        return  New(TestDescription).descriptionsFor(getEmployeeClass(),
//                                                     New(StringDescription).accessor("name").maxSize(50).beRequired(),
//                                                     New(StringDescription).accessor("address").maxSize(200).beRequired(),
//                                                     New(StringDescription).accessor("email").maxSize(100).beRequired())
//    }

    @DescriptionModelDefinition
    def myTestDescription(){
        return New(TestDescription).descriptionsFor(getEmployeeClass(),
                                                    New(NewOperationDescription).method("setName", New(StringDescription).label("name").maxSize(50).beRequired()),
                                                    New(NewOperationDescription).method("setAddress", New(StringDescription).label("address").maxSize(200).beRequired()),
                                                    New(NewOperationDescription).method("setEmail", New(StringDescription).label("email").maxSize(100).beRequired()))
    }

//    @DescriptionModelDefinition
//    def myTestDescription2(){
//        return Employee.testsShouldValidateThat {
//                setName("name".isAString().maxSize(50).beRequired())
//                setAddress("address".isAString().maxSize(200).beRequired())
//                setEmail("email".isAString().maxSize(100).beRequired())
//            }
//    }

    Class<Employee> getEmployeeClass() {
        return Employee
    }
}
