package sandbox.payroll

import org.junit.Before
import org.junit.Test
import sandbox.payroll.payment.PaymentStyle
import sandbox.validationNotification.ValidationNotificationTestSetup
import sandbox.validationNotification.builder.imp.GenericBuilder

class EmployeeUnitTest implements ValidationNotificationTestSetup{

    private Employee employeeForChange
    private static EXPECTED_PAYMENT_DATA = [] as PaymentStyle
    private static EXPECTED_PAYMENT_DATA_2 = [] as PaymentStyle

    @Before
    public void setUp(){
        ValidationNotificationTestSetup.super.setUp()
        employeeForChange = getEmployeeForChange()
    }

    Employee getEmployeeForChange(){
        return new GenericBuilder(getEmployeeClass()).setName("test name")
                                                     .setAddress("test address")
                                                     .setEmail("test email")
                                                     .setPaymentData(EXPECTED_PAYMENT_DATA)
                                                     .build()
    }

    @Test
    public void "Create employee not providing mandatory information"(){
        new GenericBuilder(getEmployeeClass()).build()
        verifyMandatoryErrorsMessagesWereIssued()
    }

    @Test
    public void "Create employee providing mandatory information"(){
        def EmployeeBuilder = new GenericBuilder(getEmployeeClass())
        def builtEmployee = EmployeeBuilder.setName("test name")
                                           .setAddress("test address")
                                           .setEmail("test email")
                                           .setPaymentData(EXPECTED_PAYMENT_DATA)
                                           .build()

        verifyEmployeeWithExpectedData(builtEmployee, "test name", "test address", "test email", EXPECTED_PAYMENT_DATA)
    }

    @Test
    public void "Change employee not providing mandatory information"(){
        employeeForChange.setName(null)
        employeeForChange.setEmail(null)
        employeeForChange.setAddress(null)
        employeeForChange.setPaymentData(null)
        verifyMandatoryErrorsMessagesWereIssued()
    }

    @Test
    public void "Change employee providing mandatory information"(){
        employeeForChange.setName("test name 2")
        employeeForChange.setEmail("test email 2")
        employeeForChange.setAddress("test address 2")
        employeeForChange.setPaymentData(EXPECTED_PAYMENT_DATA_2)
        verifyEmployeeWithExpectedData(employeeForChange, "test name 2", "test address 2", "test email 2", EXPECTED_PAYMENT_DATA_2)
    }

    private void verifyMandatoryErrorsMessagesWereIssued() {
        assert validationObserver.getErrors().contains("payroll.employee.name.mandatory")
        assert validationObserver.getErrors().contains("payroll.employee.address.mandatory")
        assert validationObserver.getErrors().contains("payroll.employee.email.mandatory")
        assert validationObserver.getErrors().contains("payroll.employee.payment.basic.info.mandatory")
    }

    private void verifyEmployeeWithExpectedData(builtEmployee, String name, String address, String email, paymentData) {
        assert validationObserver.successful()
        assert builtEmployee.getName() == name
        assert builtEmployee.getAddress() == address
        assert builtEmployee.getEmail() == email
        assert builtEmployee.getPaymentData() == paymentData
    }

    Class<Employee> getEmployeeClass() {
        return Employee
    }
}
