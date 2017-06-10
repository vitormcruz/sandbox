package com.vmc.sandbox.payroll.external.presentation.webservice.springmvc

import com.vmc.sandbox.concurrency.ModelSnapshot
import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.Repository
import com.vmc.sandbox.payroll.external.presentation.converter.EmployeeJsonConverter
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
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
        def listener = getValidationListener()
        GenericBuilder employeeBuilder = EmployeeJsonConverter.builderFromJson(request.body())
        employeeBuilder.buildAndDoOnSuccess { newEmployee ->
            employeeRepository.add(newEmployee)
            listener.setBody(newEmployee.asJson())
            model.save()
        }
        listener.fillResponse(response);
    }

    void changeEmployee(Request request, Response response) {
        SparkControllerValidationListener listener = getValidationListener()
        def changedEmployee = getResource(employeeId, employeeRepository)
        changedEmployee.applySetMap(changedAttributes)
        if(listener.successful()){
            employeeRepository.update(changedEmployee)
            listener.setBody(changedEmployee)
            model.save()
        }

        listener.fillResponse(response)
    }

    ResponseEntity<Employee> deleteEmployee(@PathVariable Long employeeId) {
        SparkControllerValidationListener listener = getValidationListener()
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
