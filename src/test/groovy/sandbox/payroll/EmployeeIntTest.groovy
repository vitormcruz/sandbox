package sandbox.payroll

import com.querydsl.core.support.QueryBase
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import sandbox.concurrency.ModelSnapshot
import sandbox.payroll.external.interfaceAdapter.persistence.querydsl.entity.QEmployee
import sandbox.payroll.payment.Commission
import sandbox.payroll.payment.Hourly
import sandbox.payroll.payment.Monthly
import sandbox.payroll.payment.TimeCard

class EmployeeIntTest implements IntegrationTestBase{

    private EmployeeRepository employeeRepository = EmployeeRepository.smartNewFor(EmployeeIntTest)
    private ModelSnapshot model = ModelSnapshot.smartNewFor(EmployeeIntTest)
    private EmployeeDataSetBuilder employeeDataSetBuilder = new EmployeeDataSetBuilder(getEmployeeClass())
    private Employee employee1
    private Employee employee2
    private Employee employee3
    private Employee employee4
    private Employee employee5

    @Before
    public void setUp(){
        IntegrationTestBase.super.setUp()
        employee1 = employeeDataSetBuilder.createNewEmployee("Heloísa", "Street 1", "heloisa@bla.com", new Monthly(2000))
        employee2 = employeeDataSetBuilder.createNewEmployee("Heloísa Medina", "test address", "test email", new Commission(2000, 100))
        employee3 = employeeDataSetBuilder.createNewEmployee("Sofia", "test address", "test email", new Monthly(2000))
        employee4 = employeeDataSetBuilder.createNewEmployee("Sofia Medina", "test address", "test email", new Monthly(2000))
        employee5 = employeeDataSetBuilder.createNewEmployee("Sofia Medina Carvalho", "test address", "test email", new Hourly(100))
    }

    @Test
    def void "Get an Employee"(){
        def retrievedEmployee = employeeRepository.get(employee1.id)
        assertMonthlyPaidEmployeeIs(retrievedEmployee, "Heloísa", "Street 1", "heloisa@bla.com", 2000)
    }

    @Test
    def void "Add a new monthly paid Employee"(){
        def addedEmployee = employeeDataSetBuilder.createNewEmployee("New Employee", "test adress", "test email", new Monthly(1000))
        assertMonthlyPaidEmployeeIs(addedEmployee, "New Employee", "test adress", "test email", 1000)
    }

    @Test
    def void "Add a new hourly paid Employee"(){
        def addedEmployee = employeeDataSetBuilder.createNewEmployee("New Employee", "test adress", "test email", new Hourly(50))
        assertHourlyPaidEmployeeIs(addedEmployee, "New Employee", "test adress", "test email", 50)
    }

    @Test
    def void "Add a new commission paid Employee"(){
        def addedEmployee = employeeDataSetBuilder.createNewEmployee("New Employee", "test adress", "test email", new Commission(1000, 20))
        assertCommissionPaidEmployeeIs(addedEmployee, "New Employee", "test adress", "test email", 1000, 20)
    }

    @Test
    def void "Edit an Employee"(){
        def employeeToChange = employeeRepository.get(employee1.id)
        employeeToChange.name = "Change Test"
        employeeToChange.address = "Change Test adress"
        employeeToChange.email = "Change Test email"
        employeeToChange.paymentData = new Monthly(5000)
        employeeRepository.update(employeeToChange)
        model.save()
        def changedEmployee = employeeRepository.get(employeeToChange.id)
        assertMonthlyPaidEmployeeIs(changedEmployee, "Change Test", "Change Test adress", "Change Test email", 5000)
    }

    @Test
    def void "Remove an Employee"(){
        employeeRepository.remove(employee1)
        assert employeeRepository.get(employee1.id) == null
    }

    @Test
    def void "Find Employees"(){
        def employeeFound = employeeRepository.findAll { QueryBase<Employee> employeeQuery, QEmployee qEmployee ->
            employeeQuery.where(qEmployee.name.like("%Medina%"))
        }

        assert employeeFound.collect {it.id} as Set == [employee2, employee4, employee5].collect {it.id} as Set
    }

    @Test
    def void "Post a time card"(){
        def expectedDate = new DateTime()
        def expectedTimeCard = new TimeCard(expectedDate, 6)
        employee5.paymentData.postPaymentInfo(expectedTimeCard)
        employeeRepository.update(employee5)
        model.save()
        def employeeChanged = employeeRepository.get(employee5.id)
        assert validationObserver.successful()
        assert employeeChanged.paymentData.getPaymentInfos().collect{ it.getDate().toString() + "_" + it.getHours()} ==
               [expectedDate.toString() + "_" + 6]
    }

    //TODO post a commission

    private void assertMonthlyPaidEmployeeIs(Employee retrievedEmployee, String expectedEmployeeName,
                                             String expectedEmployeeAddress,
                                             String expectedEmployeeEmail,
                                             Integer expectedSalary) {

        assertBasicEmployeeIs(retrievedEmployee, expectedEmployeeName, expectedEmployeeAddress, expectedEmployeeEmail)
        assert retrievedEmployee.paymentData.salary == expectedSalary
    }

    private void assertHourlyPaidEmployeeIs(Employee retrievedEmployee, String expectedEmployeeName,
                                            String expectedEmployeeAddress,
                                            String expectedEmployeeEmail,
                                            Integer expectedHourRate) {

        assertBasicEmployeeIs(retrievedEmployee, expectedEmployeeName, expectedEmployeeAddress, expectedEmployeeEmail)
        assert retrievedEmployee.paymentData.hourRate == expectedHourRate
    }

    private void assertCommissionPaidEmployeeIs(Employee retrievedEmployee, String expectedEmployeeName,
                                               String expectedEmployeeAddress,
                                               String expectedEmployeeEmail,
                                               Integer expectedSalary, Integer expectedCommissionRate) {

        assertBasicEmployeeIs(retrievedEmployee, expectedEmployeeName, expectedEmployeeAddress, expectedEmployeeEmail)
        assert retrievedEmployee.paymentData.salary == expectedSalary
        assert retrievedEmployee.paymentData.commissionRate == expectedCommissionRate
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
