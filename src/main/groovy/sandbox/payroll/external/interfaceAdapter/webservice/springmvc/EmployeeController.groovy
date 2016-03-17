package sandbox.payroll.external.interfaceAdapter.webservice.springmvc
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sandbox.payroll.business.ModelSnapshot
import sandbox.payroll.business.entity.Employee
import sandbox.payroll.business.entity.repository.EmployeeRepository

@RequestMapping(value = "payroll")
@RestController
class EmployeeController{

    private EmployeeRepository employeeRepository = EmployeeRepository.smartNewFor(EmployeeController)
    private ModelSnapshot model = ModelSnapshot.smartNewFor(EmployeeController)
    private ModelMapper modelMapper = ModelMapper.smartNewFor(EmployeeController)

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    Employee newEmployee(@RequestBody Employee newEmployee) {
        employeeRepository.add(newEmployee)
        model.save()
        return newEmployee;
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.PATCH)
    Employee changeEmployee(@PathVariable Long employeeId, @RequestBody String changedAttributes) {
        def changedEmployee = getResource(employeeId)
        patchChangedAttributesInto(changedEmployee, changedAttributes)
        employeeRepository.update(changedEmployee)
        model.save()
        return changedEmployee;
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.DELETE)
    ResponseEntity<Employee> deleteEmployee(@PathVariable Long employeeId) {
        Employee changedEmployee = getResource(employeeId)
        employeeRepository.remove(changedEmployee)
        model.save()
        return ResponseEntity.ok(changedEmployee);
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

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    Collection<Employee> listEmployees() {
        return employeeRepository
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND) //It is a lame that spring mvc do not provide such general exception
    public static class ResourceNotFoundException extends RuntimeException{

    }

}
