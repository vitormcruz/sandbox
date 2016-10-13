package sandbox.payroll

import com.querydsl.core.support.QueryBase
import org.junit.Before
import org.junit.Test
import sandbox.concurrency.ModelSnapshot
import sandbox.payroll.external.interfaceAdapter.persistence.querydsl.entity.QEmployee
import sandbox.payroll.imp.EmployeeImp
import sandbox.payroll.imp.Salary
import sandbox.validationNotification.builder.GenericBuilder

class EmployeeIntTest implements IntegrationTestBase{

    private EmployeeRepository employeeRepository = EmployeeRepository.smartNewFor(EmployeeIntTest)
    private ModelSnapshot model = ModelSnapshot.smartNewFor(EmployeeIntTest)
    private Employee employee1
    private Employee employee2
    private Employee employee3
    private Employee employee4
    private Employee employee5

    @Before
    public void setUp(){
        IntegrationTestBase.super.setUp()
        employee1 = createNewEmployee("Heloísa", "Street 1", "heloisa@bla.com", new Salary(2000))
        employee2 = createNewEmployee("Heloísa Medina", "test address", "test email", new Salary(2000))
        employee3 = createNewEmployee("Sofia", "test address", "test email", new Salary(2000))
        employee4 = createNewEmployee("Sofia Medina", "test address", "test email", new Salary(2000))
        employee5 = createNewEmployee("Sofia Medina Carvalho", "test address", "test email", new Salary(2000))
        model.save()
    }

    @Test
    def void "Get an Employee"(){
        def retrievedEmployee = employeeRepository.get(employee1.id)
        assert retrievedEmployee != null
        assert retrievedEmployee.name == "Heloísa"
        assert retrievedEmployee.address == "Street 1"
        assert retrievedEmployee.email == "heloisa@bla.com"
        assert retrievedEmployee.paymentMethod.value == 2000
    }

    @Test
    def void "Add a new Employee"(){
        def addedEmployee = createNewEmployee("New Employee", "test adress", "test email", new Salary(1000))
        model.save()
        assert addedEmployee != null
        assert addedEmployee.id != null
        assert addedEmployee.name == "New Employee"
        assert addedEmployee.address == "test adress"
        assert addedEmployee.email == "test email"
        assert addedEmployee.paymentMethod.value == 1000
    }

    @Test
    def void "Edit an Employee"(){
        def employeeToChange = employeeRepository.get(employee1.id)
        employeeToChange.name = "Change Test"
        employeeToChange.address = "Change Test adress"
        employeeToChange.email = "Change Test email"
        employeeToChange.paymentMethod = new Salary(5000)
        employeeRepository.update(employeeToChange)
        model.save()
        def changedEmployee = employeeRepository.get(employeeToChange.id)
        assert changedEmployee.name == "Change Test"
        assert changedEmployee.address == "Change Test adress"
        assert changedEmployee.email == "Change Test email"
        assert changedEmployee.paymentMethod.value == 5000
    }

    @Test
    def void "Remove an Employee"(){
        employeeRepository.remove(employee1)
        model.save()
        assert employeeRepository.get(employee1.id) == null
    }

    @Test
    def void "Find Employees"(){
        def employeeFound = employeeRepository.findAll { QueryBase<Employee> employeeQuery, QEmployee qEmployee ->
            employeeQuery.where(qEmployee.name.like("%Medina%"))
        }

        assert employeeFound.collect {it.id} as Set == [employee2, employee4, employee5].collect {it.id} as Set
    }

    private Employee createNewEmployee(String name, String address, String email, paymentMethod) {
        GenericBuilder employeeBuilder = new GenericBuilder(getEmployeeClass()).withName(name)
                .withAddress(address)
                .withEmail(email)
                .withPaymentMethod(paymentMethod)

        return employeeBuilder.buildAndDoOnSuccess({ employeeRepository.add(it) })
    }

    Class<Employee> getEmployeeClass() {
        return EmployeeImp
    }
}
