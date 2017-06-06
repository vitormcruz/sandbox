package com.vmc.sandbox.payroll

import com.vmc.sandbox.payroll.payment.type.Commission
import com.vmc.sandbox.payroll.payment.type.Monthly
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder
import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import org.junit.Before
import org.junit.Test

class EmployeeUnitTest extends ValidationNotificationTestSetup{

    private Employee employeeForChange

    @Before
    public void setUp(){
        super.setUp()
        employeeForChange = getEmployeeForChange()
    }

    Employee getEmployeeForChange(){
        return new GenericBuilder(getEmployeeClass()).setName("test name")
                                                     .setAddress("test address")
                                                     .setEmail("test email")
                                                     .bePaid(Monthly, 1000)
                                                     .build()
    }

    @Test
    public void "Create employee not providing mandatory information"(){
        new GenericBuilder(getEmployeeClass()).build()
        verifyMandatoryErrorsMessagesForCreationWereIssued()
    }

    @Test
    public void "Create employee providing mandatory information"(){
        def EmployeeBuilder = new GenericBuilder(getEmployeeClass())
        Employee builtEmployee = EmployeeBuilder.setName("test name")
                                           .setAddress("test address")
                                           .setEmail("test email")
                                           .bePaid(Monthly, 1000)
                                           .build()

        verifyEmployeeWithExpectedData(builtEmployee, "test name", "test address", "test email")
        assert builtEmployee.paymentType.class == Monthly
        assert builtEmployee.paymentType.getSalary() == 1000
    }

    @Test
    public void "Change employee not providing mandatory information"(){
        employeeForChange.setName(null)
        employeeForChange.setEmail(null)
        employeeForChange.setAddress(null)
        verifyMandatoryErrorsMessagesForChangingWereIssued()
    }

    @Test
    public void "Change employee providing mandatory information"(){
        employeeForChange.setName("test name 2")
        employeeForChange.setEmail("test email 2")
        employeeForChange.setAddress("test address 2")
        employeeForChange.bePaid(Commission, 1000, 100)
        verifyEmployeeWithExpectedData(employeeForChange, "test name 2", "test address 2", "test email 2")
        assert employeeForChange.getPaymentType().getClass() == Commission
        assert employeeForChange.getPaymentType().getSalary() == 1000
        assert employeeForChange.getPaymentType().getCommissionRate() == 100
    }

    @Test
    public void "By default, employee should not be member of Union"(){
        assert !getEmployeeForChange().isUnionMember() : "Should not be an union member by default"
    }

    @Test
    public void "Validate register Union association"(){
        employeeForChange.beUnionMember(5)
        assert employeeForChange.isUnionMember() : "Should be an union member"
    }

    @Test
    public void "Validate de-register Union association"(){
        employeeForChange.beUnionMember(5)
        employeeForChange.dropUnionMembership()
        assert !employeeForChange.isUnionMember() : "Should not be an union member after de-registration"
    }

    private void verifyMandatoryErrorsMessagesForCreationWereIssued() {
        verifyMandatoryErrorsMessagesForChangingWereIssued()
        assert validationObserver.getErrors().contains("payroll.employee.payment.type.mandatory")
    }

    private void verifyMandatoryErrorsMessagesForChangingWereIssued() {
        assert validationObserver.getErrors().contains("payroll.employee.name.mandatory")
        assert validationObserver.getErrors().contains("payroll.employee.address.mandatory")
        assert validationObserver.getErrors().contains("payroll.employee.email.mandatory")
    }


    private void verifyEmployeeWithExpectedData(builtEmployee, String name, String address, String email) {
        assert validationObserver.successful()
        assert builtEmployee.getName() == name
        assert builtEmployee.getAddress() == address
        assert builtEmployee.getEmail() == email
    }

    Class<Employee> getEmployeeClass() {
        return Employee
    }
}
