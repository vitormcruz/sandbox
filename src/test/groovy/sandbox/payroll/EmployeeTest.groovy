package sandbox.payroll

import org.junit.Before
import org.junit.Test
import sandbox.payroll.imp.EmployeeImp
import sandbox.validationNotification.ValidationNotificationTestSetup
import sandbox.validationNotification.builder.GenericValidationNotifierBuilder

class EmployeeTest implements ValidationNotificationTestSetup{

    private Employee employeeForChange
    private static EXPECTED_PAYMENT_METHOD = [] as PaymentMethod
    private static EXPECTED_PAYMENT_METHOD_2 = [] as PaymentMethod

    @Before
    public void setUp(){
        ValidationNotificationTestSetup.super.setUp()
        employeeForChange = getEmployeeForChange()
    }

    Employee getEmployeeForChange(){
        return new GenericValidationNotifierBuilder(getEmployeeClass()).withName("test name")
                                                                       .withAddress("test address")
                                                                       .withEmail("test email")
                                                                       .withPaymentMethod(EXPECTED_PAYMENT_METHOD)
                                                                       .buildEntity()
    }

    @Test
    public void "Create employee not providing mandatory information"(){
        new GenericValidationNotifierBuilder(getEmployeeClass()).buildEntity()
        verifyMandatoryErrorsMessagesWereIssued()
    }

    @Test
    public void "Create employee providing mandatory information"(){
        def EmployeeBuilder = new GenericValidationNotifierBuilder(getEmployeeClass())
        def builtEmployee = EmployeeBuilder.withName("test name")
                                           .withAddress("test address")
                                           .withEmail("test email")
                                           .withPaymentMethod(EXPECTED_PAYMENT_METHOD)
                                           .buildEntity()

        verifyEmployeeWithExpectedData(builtEmployee, "test name", "test address", "test email", EXPECTED_PAYMENT_METHOD)
    }

    @Test
    public void "Change employee not providing mandatory information"(){
        employeeForChange.setName(null)
        employeeForChange.setEmail(null)
        employeeForChange.setAddress(null)
        employeeForChange.setPaymentMethod(null)
        verifyMandatoryErrorsMessagesWereIssued()
    }

    @Test
    public void "Change employee providing mandatory information"(){
        employeeForChange.setName("test name 2")
        employeeForChange.setEmail("test email 2")
        employeeForChange.setAddress("test address 2")
        employeeForChange.setPaymentMethod(EXPECTED_PAYMENT_METHOD_2)
        verifyEmployeeWithExpectedData(employeeForChange, "test name 2", "test address 2", "test email 2", EXPECTED_PAYMENT_METHOD_2)
    }

    private void verifyMandatoryErrorsMessagesWereIssued() {
        assert validationObserver.getErrors().contains("payroll.employee.name.mandatory")
        assert validationObserver.getErrors().contains("payroll.employee.address.mandatory")
        assert validationObserver.getErrors().contains("payroll.employee.email.mandatory")
        assert validationObserver.getErrors().contains("payroll.employee.paymentMethod.mandatory")
    }

    private void verifyEmployeeWithExpectedData(builtEmployee, String name, String address, String email, paymentMethod) {
        assert validationObserver.successful()
        assert builtEmployee.getName() == name
        assert builtEmployee.getAddress() == address
        assert builtEmployee.getEmail() == email
        assert builtEmployee.getPaymentMethod() == paymentMethod
    }

    Class<EmployeeImp> getEmployeeClass() {
        return EmployeeImp
    }
}
