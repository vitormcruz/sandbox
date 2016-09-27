package sandbox.payroll

import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.ValidationObserver
import sandbox.validationNotification.builder.GenericValidationNotifierBuilder

class EmployeeTest {

    private SimpleValidationObserver validationObserver

    @BeforeClass
    def static void setUpAll(){
        ApplicationValidationNotifier.createCurrentListOfListeners()
    }

    @AfterClass
    def static void tearDownAll(){
        ApplicationValidationNotifier.destroyCurrentListOfListeners()
    }

    @Before
    def void setUp(){
        validationObserver = new SimpleValidationObserver()
        ApplicationValidationNotifier.addObserver(this.validationObserver)
    }

    @Test
    public void "Create employee not providing mandatory information"(){
        new GenericValidationNotifierBuilder(getEmployeeClass()).buildEntity()
        assert validationObserver.getErrors().contains("payroll.employee.name.mandatory")
        assert validationObserver.getErrors().contains("payroll.employee.address.mandatory")
        assert validationObserver.getErrors().contains("payroll.employee.email.mandatory")
        assert validationObserver.getErrors().contains("payroll.employee.paymentMethod.mandatory")
    }

    @Test
    public void "Create employee providing mandatory information"(){
        def EmployeeBuilder = new GenericValidationNotifierBuilder(getEmployeeClass())
        def expectedPaymentMethod = [] as PaymentMethod
        def builtEmployee = EmployeeBuilder.withName("test name")
                                           .withAddress("test address")
                                           .withEmail("test email")
                                           .withPaymentMethod(expectedPaymentMethod)
                                           .buildEntity()

        assert validationObserver.successful()
        assert builtEmployee.getName() == "test name"
        assert builtEmployee.getAddress() == "test address"
        assert builtEmployee.getEmail() == "test email"
        assert builtEmployee.getPaymentMethod() == expectedPaymentMethod
    }

    Class<EmployeeImp> getEmployeeClass() {
        return EmployeeImp
    }

    public static class SimpleValidationObserver implements ValidationObserver{

        def errors = []
        def mandatoryObligation = [:]

        @Override
        void startValidation(String validationName) {

        }

        @Override
        void issueMandatoryObligation(String mandatoryValidationName, String error) {
            mandatoryObligation.put(mandatoryValidationName, error)
        }

        @Override
        void issueMandatoryObligationComplied(String mandatoryValidationName) {
            mandatoryObligation.remove(mandatoryValidationName)
        }

        @Override
        void issueError(String error) {
            errors.add(error)
        }

        @Override
        void finishValidation(String validationName) {

        }

        @Override
        Boolean successful() {
            return errors.isEmpty()
        }

        def getErrors() {
            return errors + mandatoryObligation.collect {it.value}
        }
    }
}
