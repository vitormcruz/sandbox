package com.vmc.sandbox.payroll

import com.vmc.sandbox.concurrency.ModelSnapshot
import com.vmc.sandbox.payroll.external.persistence.inMemory.repository.CommonInMemoryRepository
import com.vmc.sandbox.payroll.payment.attachment.SalesReceipt
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

    private CommonInMemoryRepository<Employee> employeeRepository
    private ModelSnapshot model
    private DataSetBuilder employeeDataSetBuilder
    private Employee employee1
    private Employee employee2
    private Employee employee3
    private Employee employee4
    private Employee employee5

    @Before
    public void setUp(){
        super.setUp()
        employeeRepository = CommonInMemoryRepository.smartNewFor(EmployeeIntTest)
        model = ModelSnapshot.smartNewFor(EmployeeIntTest)
        employeeDataSetBuilder = new DataSetBuilder(getEmployeeClass(), {
            employeeRepository.add(it)
            model.save()
        })
        employee1 = employeeDataSetBuilder.setName("Heloísa").setAddress("Street 1").setEmail("heloisa@bla.com").setPaymentType(new Monthly(2000)).build()
        employee2 = employeeDataSetBuilder.setName("Heloísa Medina").setAddress("test address").setEmail("test email").setPaymentType(new Commission(2000, 100)).build()
        employee3 = employeeDataSetBuilder.setName("Sofia").setAddress("test address").setEmail("test email").setPaymentType(new Monthly(2000)).build()
        employee4 = employeeDataSetBuilder.setName("Sofia Medina").setAddress("test address").setEmail("test email").setPaymentType(new Monthly(2000)).build()
        employee5 = employeeDataSetBuilder.setName("Sofia Medina Carvalho").setAddress("test address").setEmail("test email").setPaymentType(new Hourly(100)).build()
    }

    @Test
    def void "Get an Employee"(){
        def retrievedEmployee = employeeRepository.get(employee1.id)
        assertMonthlyPaidEmployeeIs(retrievedEmployee, "Heloísa", "Street 1", "heloisa@bla.com", 2000)
    }

    @Test
    def void "Add a new monthly paid Employee"(){
        Employee addedEmployee = employeeDataSetBuilder.setName("New Employee").setAddress("test adress").setEmail("test email").setPaymentType(new Monthly(1000)).build()
        addedEmployee = employeeRepository.get(addedEmployee.getId())
        assertMonthlyPaidEmployeeIs(addedEmployee, "New Employee", "test adress", "test email", 1000)
    }

    @Test
    def void "Add a new hourly paid Employee"(){
        Employee addedEmployee = employeeDataSetBuilder.setName("New Employee").setAddress("test adress").setEmail("test email").setPaymentType(new Hourly(50)).build()
        addedEmployee = employeeRepository.get(addedEmployee.getId())
        assertHourlyPaidEmployeeIs(addedEmployee, "New Employee", "test adress", "test email", 50)
    }

    @Test
    def void "Add a new commission paid Employee"(){
        Employee addedEmployee = employeeDataSetBuilder.setName("New Employee").setAddress("test adress").setEmail("test email").setPaymentType(new Commission(1000, 20)).build()
        addedEmployee = employeeRepository.get(addedEmployee.getId())
        assertCommissionPaidEmployeeIs(addedEmployee, "New Employee", "test adress", "test email", 1000, 20)
    }

    @Test
    def void "Edit an Employee"(){
        Employee employeeToChange = employeeRepository.get(employee1.id)
        employeeToChange.name = "Change Test"
        employeeToChange.address = "Change Test adress"
        employeeToChange.email = "Change Test email"
        employeeToChange.paymentType = new Monthly(5000)
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
    def void "Find Employees"(){
        def employeeFound = employeeRepository.findAll {it.name.contains("Medina")}

        assert employeeFound.collect {it.id} as Set == [employee2, employee4, employee5].collect {it.id} as Set
    }

    @Test
    def void "Post a time card"(){
        def expectedDate = new DateTime()
        def expectedTimeCard = new TimeCard(expectedDate, 6)
        employee5.postPaymentAttachment(expectedTimeCard)
        employeeRepository.update(employee5)
        model.save()
        def employeeChanged = employeeRepository.get(employee5.id)
        assert validationObserver.successful()
        assert employeeChanged.paymentType.getPaymentAttachments().collect{ it.getDate().toString() + "_" + it.getHours()} ==
               [expectedDate.toString() + "_" + 6]
    }

    @Test
    def void "Post a sales receipt"(){
        def expectedDate = new DateTime()
        def expectedSalesReceipt = new SalesReceipt(expectedDate, 200)
        employee2.postPaymentAttachment(expectedSalesReceipt)
        employeeRepository.update(employee2)
        model.save()
        def employeeChanged = employeeRepository.get(employee2.id)
        assert validationObserver.successful()
        assert employeeChanged.paymentType.getPaymentAttachments().collect{ it.getDate().toString() + "_" + it.getAmount()} ==
               [expectedDate.toString() + "_" + 200]
    }

    @Test
    def void "Post attachment to monthly paid employee"(){
        def e = shouldFail UnsupportedOperationException,
                           {employee1.postPaymentAttachment(new SalesReceipt(new DateTime(), 200))}

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
