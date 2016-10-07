package sandbox.payroll

import org.junit.Before
import org.junit.Test
import sandbox.concurrency.ModelSnapshot
import sandbox.payroll.imp.EmployeeImp
import sandbox.payroll.imp.Salary
import sandbox.validationNotification.builder.GenericBuilder

class EmployeeIntTest implements IntegrationTestBase{

    //TODO smartNew is not working here because when configuration happens this code is executed already. See if a proxy mechanism can fix
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
        employeeRepository = EmployeeRepository.smartNewFor(EmployeeIntTest)
        model = ModelSnapshot.smartNewFor(EmployeeIntTest)
        employee1 = createNewEmployee("Heloísa", "Street 1", "heloisa@bla.com", new Salary(2000))
        employee2 = createNewEmployee("Heloísa Medina", "test address", "test email", new Salary(2000))
        employee3 = createNewEmployee("Sofia", "test address", "test email", new Salary(2000))
        employee4 = createNewEmployee("Sofia Medina", "test address", "test email", new Salary(2000))
        employee5 = createNewEmployee("Sofia Medina Carvalho", "test address", "test email", new Salary(2000))
        model.save()
    }

    @Test
    def void "Get an Employee"(){
        def employeeImp = employeeRepository.get(employee1.id)
        assert employeeImp != null
        assert employeeImp.name == "Heloísa"
        assert employeeImp.address == "Street 1"
        assert employeeImp.email == "heloisa@bla.com"
        assert employeeImp.paymentMethod.value == 2000
    }

    @Test
    def void "Add a new Employee"(){
        createNewEmployee("New Employee", "test adress", "test email", new Salary(2000))


    }

    @Test
    def void "Edit an Employee"(){}

    @Test
    def void "Remove an Employee"(){}

    @Test
    def void "Find Employees"(){

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
