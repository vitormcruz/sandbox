package sandbox.payroll.external.interfaceAdapter.presentation.webservice.springmvc

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sandbox.concurrency.ModelSnapshot
import sandbox.payroll.EmployeeRepository
import sandbox.payroll.imp.EmployeeImp
import sandbox.validationNotification.builder.GenericBuilder

@RequestMapping(value = "payroll")
@RestController
class EmployeeRestController implements BasicControllerOperationsTrait{

    private EmployeeRepository employeeRepository = EmployeeRepository.smartNewFor(EmployeeRestController)
    private ModelSnapshot model = ModelSnapshot.smartNewFor(EmployeeRestController)

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    ResponseEntity<EmployeeImp> newEmployee(@RequestBody Map newEmployeeMap) {
        RestControllerValidationListener listener = getValidationListener()
        GenericBuilder employeeBuilder = new GenericBuilder(EmployeeImp).applyMap(newEmployeeMap)
        employeeBuilder.buildAndDoOnSuccess { newEmployee ->
            employeeRepository.add(newEmployee)
            listener.setBody(newEmployee)
            model.save()
        }
        return listener.generateResponse();
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.PATCH)
    ResponseEntity<EmployeeImp> changeEmployee(@PathVariable Long employeeId, @RequestBody Map changedAttributes) {
        RestControllerValidationListener listener = getValidationListener()
        def changedEmployee = getResource(employeeId, employeeRepository)
        changedEmployee.applyMap(changedAttributes)
        if(listener.successful()){
            employeeRepository.update(changedEmployee)
            listener.setBody(changedEmployee)
            model.save()
        }

        return listener.generateResponse()
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.DELETE)
    ResponseEntity<EmployeeImp> deleteEmployee(@PathVariable Long employeeId) {
        RestControllerValidationListener listener = getValidationListener()
        EmployeeImp employeeSubjectedRemoval = getResource(employeeId, employeeRepository)
        if(listener.successful()) {
            employeeRepository.remove(employeeSubjectedRemoval)
            listener.setBody(employeeSubjectedRemoval)
            model.save()
        }
        return listener.generateResponse();
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    Collection<EmployeeImp> listEmployees() {
        return employeeRepository
    }

}
