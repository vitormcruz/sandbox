package com.vmc.sandbox.payroll.external.config

import com.vmc.sandbox.concurrency.inMemory.InMemoryAtomicBlock
import com.vmc.sandbox.concurrency.inMemory.InMemoryPersistentModelSnapshot
import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.external.persistence.inMemory.repository.CommonInMemoryRepository
import com.vmc.sandbox.payroll.external.presentation.webservice.springmvc.EmployeeWebServiceController
import spark.servlet.SparkApplication

import static spark.Spark.*

class PayrollSparkConfiguration implements SparkApplication {

    EmployeeWebServiceController employeeWebServiceController = new EmployeeWebServiceController(new CommonInMemoryRepository<Employee>(),
                                                                                                 new InMemoryPersistentModelSnapshot(new InMemoryAtomicBlock()))

    @Override
    void init() {
        path("/api/payroll", {

            before("/*", {req, res -> res.type("application/json") })

            exception(IllegalArgumentException, {exception, request, response ->
                return exception.message
            })

            path("/employee", {
                post("", { req, res ->
                    employeeWebServiceController.newEmployee(req, res)
                    return res
                })
            })
        })
    }
}
