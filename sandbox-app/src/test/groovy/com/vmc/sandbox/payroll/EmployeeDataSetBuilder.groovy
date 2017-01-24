package com.vmc.sandbox.payroll

import com.vmc.sandbox.concurrency.ModelSnapshot
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder

class EmployeeDataSetBuilder {

    private EmployeeRepository employeeRepository = EmployeeRepository.smartNewFor(EmployeeDataSetBuilder)
    private ModelSnapshot model = ModelSnapshot.smartNewFor(EmployeeDataSetBuilder)
    private Class<Employee> employeeClass

    EmployeeDataSetBuilder(Class<Employee> employeeClass) {
        this.employeeClass = employeeClass
    }

    public Employee createNewEmployee(String name, String address, String email, paymentMethod) {
        GenericBuilder employeeBuilder = new GenericBuilder(employeeClass).setName(name)
                                                                          .setAddress(address)
                                                                          .setEmail(email)
                                                                          .setPaymentType(paymentMethod)

        return employeeBuilder.buildAndDoOnSuccess({
            employeeRepository.add(it)
            model.save()
        })
    }
}
