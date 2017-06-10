package com.vmc.sandbox.payroll.external.config

import com.vmc.sandbox.concurrency.inMemory.InMemoryAtomicBlock
import com.vmc.sandbox.concurrency.inMemory.InMemoryPersistentModelSnapshot
import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.external.persistence.inMemory.repository.CommonInMemoryRepository
import com.vmc.sandbox.payroll.external.presentation.webservice.springmvc.EmployeeWebServiceController
import org.apache.http.HttpStatus
import spark.servlet.SparkApplication

import static spark.Spark.*

class PayrollSparkRoutesConfiguration implements SparkApplication {

    EmployeeWebServiceController employeeWebServiceController = new EmployeeWebServiceController(new CommonInMemoryRepository<Employee>(),
                                                                                                 new InMemoryPersistentModelSnapshot(new InMemoryAtomicBlock()))

    @Override
    void init() {
        path("/api/payroll", {

            before("/*", {req, res -> res.type("application/json") })
            configureEsceptionHandling()
            configureEmployeeRoutes()
        })
    }

    private configureEsceptionHandling() {
        exception(IllegalArgumentException, { exception, request, response ->
            response.type("text/plain")
            response.status(HttpStatus.SC_BAD_REQUEST)
            response.body(exception.message)
        })
    }

    private configureEmployeeRoutes() {
        path("/employee", {

            post("", { req, res ->
                employeeWebServiceController.newEmployee(req, res)
                return res.body()
            })


        })
    }
}
