package com.vmc.sandbox.payroll

import com.vmc.sandbox.payroll.external.config.ServiceLocator
import com.vmc.sandbox.payroll.external.persistence.inMemory.repository.CommonInMemoryRepository
import com.vmc.sandbox.payroll.payment.attachment.SalesReceipt
import com.vmc.sandbox.payroll.payment.attachment.ServiceCharge
import com.vmc.sandbox.payroll.payment.attachment.TimeCard
import com.vmc.sandbox.payroll.payment.type.Commission
import com.vmc.sandbox.payroll.payment.type.Hourly
import com.vmc.sandbox.payroll.payment.type.Monthly
import com.vmc.sandbox.payroll.testPreparation.IntegrationTestBase
import com.vmc.sandbox.validationNotification.builder.imp.DataSetBuilder
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test

import static groovy.test.GroovyAssert.shouldFail

class EmployeeIntTest extends IntegrationTestBase {

    private CommonInMemoryRepository<Employee> employeeRepository = ServiceLocator.instance.employeeRepository()
    private DataSetBuilder employeeBuilder
    private Employee employee1
    private Employee employee2
    private Employee employee3
    private Employee employee4
    private Employee employeeUnion5

    @Before
    public void setUp(){
        super.setUp()
        employeeBuilder = new DataSetBuilder(getEmployeeClass(), {
            employeeRepository.add(it)
            model.save()
        })

        employee1 = employeeBuilder.withName("Heloísa").withAddress("Street 1").withEmail("heloisa@bla.com")
                                   .withPaimentArgs(Monthly, 2000).build()
        employee2 = employeeBuilder.withName("Heloísa Medina").withAddress("test address").withEmail("test email")
                                   .withPaimentArgs(Commission, 2000, 100).build()
        employee3 = employeeBuilder.withName("Sofia").withAddress("test address").withEmail("test email")
                                   .withPaimentArgs(Monthly, 2000).build()
        employee4 = employeeBuilder.withName("Sofia Medina").withAddress("test address").withEmail("test email")
                                   .withPaimentArgs(Monthly, 2000).build()
        employeeUnion5 = employeeBuilder.withName("Sofia Medina Carvalho").withAddress("test address").withEmail("test email").beUnionMember(5)
                                        .withPaimentArgs(Hourly, 100).build()
    }

    @Test
    def void "Get an Employee"(){
        def retrievedEmployee = employeeRepository.get(employee1.id)
        assertMonthlyPaidEmployeeIs(retrievedEmployee, "Heloísa", "Street 1", "heloisa@bla.com", 2000)
    }

    @Test
    def void "Add a new monthly paid Employee"(){
        Employee addedEmployee = employeeBuilder.withName("New Employee").withAddress("test adress").withEmail("test email").withPaimentArgs(Monthly, 1000).build()
        addedEmployee = employeeRepository.get(addedEmployee.getId())
        assertMonthlyPaidEmployeeIs(addedEmployee, "New Employee", "test adress", "test email", 1000)
    }

    @Test
    def void "Add a new hourly paid Employee"(){
        Employee addedEmployee = employeeBuilder.withName("New Employee").withAddress("test adress").withEmail("test email").withPaimentArgs(Hourly, 50).build()
        addedEmployee = employeeRepository.get(addedEmployee.getId())
        assertHourlyPaidEmployeeIs(addedEmployee, "New Employee", "test adress", "test email", 50)
    }

    @Test
    def void "Edit an Employee"(){
        Employee employeeToChange = employeeRepository.get(employee1.id)
        employeeToChange.name = "Change Test"
        employeeToChange.address = "Change Test adress"
        employeeToChange.email = "Change Test email"
        employeeToChange.bePaid(Monthly, 5000)
        employeeRepository.update(employeeToChange)
        model.save()
        def changedEmployee = employeeRepository.get(employeeToChange.id)
        assertMonthlyPaidEmployeeIs(changedEmployee, "Change Test", "Change Test adress", "Change Test email", 5000)
    }

    @Test
    def void "Remove an Employee"(){
        employeeRepository.remove(employee1)
        model.save()
        assert employeeRepository.get(employee1.id) == null
    }

    @Test
    def void "Add a new Union member Employee"(){
        def addedEmployee = employeeBuilder.withName("New Employee").withAddress("test adress").withEmail("test email")
                                           .withPaimentArgs(Monthly, 1000).beUnionMember(5).build()
        assertMonthlyPaidEmployeeIs(addedEmployee, "New Employee", "test adress", "test email", 1000)
        assert addedEmployee.isUnionMember() : "Should be an Union Member"
    }

    @Test
    def void "Find Employees"(){
        def employeeFound = employeeRepository.findAll {it.name.contains("Medina")}

        assert employeeFound.collect {it.id} as Set == [employee2, employee4, employeeUnion5].collect {it.id} as Set
    }

    @Test
    def void "Add a new commission paid Employee"(){
        Employee addedEmployee = employeeBuilder.withName("New Employee").withAddress("test adress").withEmail("test email").withPaimentArgs(Commission, 1000, 20).build()
        addedEmployee = employeeRepository.get(addedEmployee.getId())
        assertCommissionPaidEmployeeIs(addedEmployee, "New Employee", "test adress", "test email", 1000, 20)
    }

    @Test
    def void "Post a time card"(){
        def expectedDate = new DateTime()
        def expectedTimeCard = TimeCard.newTimeCard(expectedDate, 6)
        employeeUnion5.postWorkEvent(expectedTimeCard)
        employeeRepository.update(employeeUnion5)
        model.save()
        def employeeChanged = employeeRepository.get(employeeUnion5.id)
        assert validationObserver.successful() : "${validationObserver.getCommaSeparatedErrors()}"
        assert employeeChanged.paymentType.getPaymentAttachments().collect{ it.getDate().toString() + "_" + it.getHours()} ==
               [expectedDate.toString() + "_" + 6]
    }

    @Test
    def void "Post a sales receipt"(){
        def expectedDate = new DateTime()
        def expectedSalesReceipt = SalesReceipt.newSalesReceipt(expectedDate, 200)
        employee2.postWorkEvent(expectedSalesReceipt)
        employeeRepository.update(employee2)
        model.save()
        def employeeChanged = employeeRepository.get(employee2.id)
        assert validationObserver.successful() : "${validationObserver.getCommaSeparatedErrors()}"
        assert employeeChanged.paymentType.getPaymentAttachments().collect{ it.getDate().toString() + "_" + it.getAmount()} ==
               [expectedDate.toString() + "_" + 200]
    }

    @Test
    def void "Post an Union charge"(){
        def expectedDate = new DateTime()
        def expectedServiceCharge = ServiceCharge.newServiceCharge(expectedDate, 5)
        employeeUnion5.postWorkEvent(expectedServiceCharge)
        employeeRepository.update(employeeUnion5)
        model.save()
        def employeeChanged = employeeRepository.get(employeeUnion5.id)
        assert validationObserver.successful() : "${validationObserver.getCommaSeparatedErrors()}"
        assert employeeChanged.unionAssociation.getCharges().collect{ it.getDate().toString() + "_" + it.getAmount()} ==
                [expectedDate.toString() + "_" + 5]
    }

    @Test
    def void "Post attachment to monthly paid employee"(){
        def e = shouldFail UnsupportedOperationException,
                           {employee1.postWorkEvent(SalesReceipt.newSalesReceipt(new DateTime(), 200))}

        assert e.getMessage() == "Monthly payment does not have payment attachments"
    }

    private void assertMonthlyPaidEmployeeIs(Employee retrievedEmployee, String expectedEmployeeName,
                                             String expectedEmployeeAddress,
                                             String expectedEmployeeEmail,
                                             Integer expectedSalary) {

        assertBasicEmployeeIs(retrievedEmployee, expectedEmployeeName, expectedEmployeeAddress, expectedEmployeeEmail)
        assert retrievedEmployee.paymentType.salary == expectedSalary
    }

    private void assertHourlyPaidEmployeeIs(Employee retrievedEmployee, String expectedEmployeeName,
                                            String expectedEmployeeAddress,
                                            String expectedEmployeeEmail,
                                            Integer expectedHourRate) {

        assertBasicEmployeeIs(retrievedEmployee, expectedEmployeeName, expectedEmployeeAddress, expectedEmployeeEmail)
        assert retrievedEmployee.paymentType.hourRate == expectedHourRate
    }

    private void assertCommissionPaidEmployeeIs(Employee retrievedEmployee, String expectedEmployeeName,
                                               String expectedEmployeeAddress,
                                               String expectedEmployeeEmail,
                                               Integer expectedSalary, Integer expectedCommissionRate) {

        assertBasicEmployeeIs(retrievedEmployee, expectedEmployeeName, expectedEmployeeAddress, expectedEmployeeEmail)
        assert retrievedEmployee.paymentType.salary == expectedSalary
        assert retrievedEmployee.paymentType.commissionRate == expectedCommissionRate
    }

    private void assertBasicEmployeeIs(Employee retrievedEmployee, String expectedEmployeeName, String expectedEmployeeAddress, String expectedEmployeeEmail) {
        assert retrievedEmployee != null
        assert retrievedEmployee.id != null
        assert retrievedEmployee.name == expectedEmployeeName
        assert retrievedEmployee.address == expectedEmployeeAddress
        assert retrievedEmployee.email == expectedEmployeeEmail
    }

    Class<Employee> getEmployeeClass() {
        return Employee
    }
}
