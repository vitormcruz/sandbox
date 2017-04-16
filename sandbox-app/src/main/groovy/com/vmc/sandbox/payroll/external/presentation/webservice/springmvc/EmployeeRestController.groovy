package com.vmc.sandbox.payroll.external.presentation.webservice.springmvc

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.vmc.sandbox.concurrency.ModelSnapshot
import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.EmployeeRepository
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder

@RequestMapping(value = "api/payroll/")
@RestController
class EmployeeRestController implements BasicControllerOperationsTrait{

    private EmployeeRepository employeeRepository = EmployeeRepository.smartNewFor(EmployeeRestController)
    private ModelSnapshot model = ModelSnapshot.smartNewFor(EmployeeRestController)

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    ResponseEntity<Employee> newEmployee(@RequestBody Map newEmployeeMap) {
        RestControllerValidationListener listener = getValidationListener()
        GenericBuilder employeeBuilder = new GenericBuilder(Employee).applyMap(newEmployeeMap)
        employeeBuilder.buildAndDoOnSuccess { newEmployee ->
            employeeRepository.add(newEmployee)
            listener.setBody(newEmployee)
            model.save()
        }
        return listener.generateResponse();
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.PATCH)
    ResponseEntity<Employee> changeEmployee(@PathVariable Long employeeId, @RequestBody Map changedAttributes) {
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
    ResponseEntity<Employee> deleteEmployee(@PathVariable Long employeeId) {
        RestControllerValidationListener listener = getValidationListener()
        Employee employeeSubjectedRemoval = getResource(employeeId, employeeRepository)
        if(listener.successful()) {
            employeeRepository.remove(employeeSubjectedRemoval)
            listener.setBody(employeeSubjectedRemoval)
            model.save()
        }
        return listener.generateResponse();
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    Collection<Employee> listEmployees() {
        return employeeRepository
    }

}
