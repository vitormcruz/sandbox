package sandbox.payroll.external.interfaceAdapter.webservice.springmvc
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sandbox.payroll.Employee
import sandbox.payroll.EmployeeRepository
import sandbox.payroll.ModelSnapshot
import sandbox.validationNotification.ApplicationValidationNotifier

@RequestMapping(value = "payroll")
@RestController
class EmployeeRestController {

    private EmployeeRepository employeeRepository = EmployeeRepository.smartNewFor(EmployeeRestController)
    private ModelSnapshot model = ModelSnapshot.smartNewFor(EmployeeRestController)
    private ModelMapper modelMapper = ModelMapper.smartNewFor(EmployeeRestController)

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    ResponseEntity<Employee> newEmployee(@RequestBody Map mandatoryEmployeeData) {
        RestControllerValidationListener listener = getValidationListener()
        def newEmployee = Employee.newEmployee(mandatoryEmployeeData)
        if(listener.successful()){
            employeeRepository.add(newEmployee)
            model.save()
        }
        return listener.generateResponse(newEmployee);
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.PATCH)
    ResponseEntity<Employee> changeEmployee(@PathVariable Long employeeId, @RequestBody String changedAttributes) {
        RestControllerValidationListener listener = getValidationListener()
        def changedEmployee = getResource(employeeId)
        patchChangedAttributesInto(changedEmployee, changedAttributes)
        if(listener.successful()){
            employeeRepository.update(changedEmployee)
            model.save()
        }

        return listener.generateResponse(changedEmployee)
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.DELETE)
    ResponseEntity<Employee> deleteEmployee(@PathVariable Long employeeId) {
        RestControllerValidationListener listener = getValidationListener()
        Employee changedEmployee = getResource(employeeId)
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

    private RestControllerValidationListener getValidationListener() {
        def listener = new RestControllerValidationListener()
        ApplicationValidationNotifier.addObserver(listener)
        listener
    }

    private Employee getResource(long employeeId) {
        def changedEmployee = employeeRepository.get(employeeId)
        if (!changedEmployee) throw new ResourceNotFoundException()
        return changedEmployee
    }

    private void patchChangedAttributesInto(Employee changedEmployee, String changedAttributes) {
        JsonNode changedAttributesNode = new ObjectMapper().readTree(changedAttributes)
        modelMapper.map(changedAttributesNode, changedEmployee)
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND) //It is a lame that spring mvc do not provide such general exception
    public static class ResourceNotFoundException extends RuntimeException{

    }

}
