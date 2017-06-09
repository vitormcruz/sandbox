package com.vmc.sandbox.payroll.external.presentation.webservice.springmvc

import com.vmc.sandbox.concurrency.ModelSnapshot
import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.Repository
import com.vmc.sandbox.payroll.external.presentation.converter.EmployeeJsonConverter
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import spark.Request
import spark.Response

class EmployeeWebServiceController implements BasicControllerOperationsTrait{

    private Repository<Employee> employeeRepository
    private ModelSnapshot model

    EmployeeWebServiceController(Repository<Employee> anEmployeeRepo, ModelSnapshot aModel) {
        this.employeeRepository = anEmployeeRepo
        this.model = aModel
    }

    void newEmployee(Request request, Response response) {
        WebServiceControllerValidationListener listener = getValidationListener()
        GenericBuilder employeeBuilder = EmployeeJsonConverter.builderFromJson(request.body())
        employeeBuilder.buildAndDoOnSuccess { newEmployee ->
            employeeRepository.add(newEmployee)
            listener.setBody(newEmployee)
            model.save()
        }
        listener.fillResponse(response);
    }

    ResponseEntity<Employee> changeEmployee(@PathVariable Long employeeId, @RequestBody Map changedAttributes) {
        WebServiceControllerValidationListener listener = getValidationListener()
        def changedEmployee = getResource(employeeId, employeeRepository)
        changedEmployee.applySetMap(changedAttributes)
        if(listener.successful()){
            employeeRepository.update(changedEmployee)
            listener.setBody(changedEmployee)
            model.save()
        }

        return listener.fillResponse()
    }

    ResponseEntity<Employee> deleteEmployee(@PathVariable Long employeeId) {
        WebServiceControllerValidationListener listener = getValidationListener()
        Employee employeeSubjectedRemoval = getResource(employeeId, employeeRepository)
        if(listener.successful()) {
            employeeRepository.remove(employeeSubjectedRemoval)
            listener.setBody(employeeSubjectedRemoval)
            model.save()
        }
        return listener.fillResponse();
    }

    Collection<Employee> listEmployees() {
        return employeeRepository
    }

}
