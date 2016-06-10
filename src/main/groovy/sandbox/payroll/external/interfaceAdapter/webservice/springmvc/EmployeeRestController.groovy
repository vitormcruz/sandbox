package sandbox.payroll.external.interfaceAdapter.webservice.springmvc
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sandbox.payroll.Employee
import sandbox.payroll.EmployeeRepository
import sandbox.payroll.ModelSnapshot

@RequestMapping(value = "payroll")
@RestController
class EmployeeRestController implements BasicControllerOperationsTrait{

    private EmployeeRepository employeeRepository = EmployeeRepository.smartNewFor(EmployeeRestController)
    private ModelSnapshot model = ModelSnapshot.smartNewFor(EmployeeRestController)

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    ResponseEntity<Employee> newEmployee(@RequestBody String newEmployeeJson) {
        RestControllerValidationListener listener = getValidationListener()
        def newEmployee = mapEntityFromJson(Employee, newEmployeeJson)
        if(listener.successful()){
            employeeRepository.add(newEmployee)
            model.save()
        }
        return listener.generateResponse(newEmployee);
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.PATCH)
    ResponseEntity<Employee> changeEmployee(@PathVariable Long employeeId, @RequestBody String changedAttributes) {
        RestControllerValidationListener listener = getValidationListener()
        def changedEmployee = getResource(employeeId, employeeRepository)
        mapEntityFromJson(changedEmployee, changedAttributes)
        if(listener.successful()){
            employeeRepository.update(changedEmployee)
            model.save()
        }

        return listener.generateResponse(changedEmployee)
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.DELETE)
    ResponseEntity<Employee> deleteEmployee(@PathVariable Long employeeId) {
        RestControllerValidationListener listener = getValidationListener()
        Employee changedEmployee = getResource(employeeId, employeeRepository)
        if(listener.successful()) {
            employeeRepository.remove(changedEmployee)
            model.save()
        }
        return listener.generateResponse(changedEmployee);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    Collection<Employee> listEmployees() {
        return employeeRepository
    }

}
