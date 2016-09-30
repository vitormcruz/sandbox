package sandbox.payroll.external.interfaceAdapter.presentation.webservice.springmvc

import org.junit.Ignore
import org.junit.Test
import sandbox.sandboxapp.external.config.main.SandboxContextConfigListener
import sandbox.validationNotification.ApplicationValidationNotifier

/**
 * Created by UR5Y on 01/07/2016.
 */
@Ignore
class EmployeeRestControllerTest {

    @Test
    def void abc(){
        ApplicationValidationNotifier.createCurrentListOfListeners()
        new SandboxContextConfigListener().contextInitialized(null)
        ApplicationValidationNotifier.destroyCurrentListOfListeners()

        ApplicationValidationNotifier.createCurrentListOfListeners()
//        new EmployeeRestController().newEmployee("{\n" +
//                "   \"name\": \"Joao1\",\n" +
//                "   \"address\" : \"Rua2\",\n" +
//                "   \"email\" : \"blabla@gmail.com\",\n" +
//                "   \"paymentMethod\": {\"salary\": \"5000\"}\n" +
//                "}")

        new EmployeeRestController().newEmployee(new HashMap<String, String>(){{
            put("name", "Joao1")
            put("address", "Rua2")
            put("email", "blabla@gmail.com")
            put("paymentMethod", ["salary": "5000"])
        }})

        ApplicationValidationNotifier.destroyCurrentListOfListeners()
    }
}
