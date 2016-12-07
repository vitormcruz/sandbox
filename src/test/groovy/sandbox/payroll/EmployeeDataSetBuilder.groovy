package sandbox.payroll

import sandbox.concurrency.ModelSnapshot
import sandbox.validationNotification.builder.imp.GenericBuilder

class EmployeeDataSetBuilder {

    private EmployeeRepository employeeRepository = EmployeeRepository.smartNewFor(EmployeeIntTest)
    private ModelSnapshot model = ModelSnapshot.smartNewFor(EmployeeIntTest)
    private Class<Employee> employeeClass

    EmployeeDataSetBuilder(Class<Employee> employeeClass) {
        this.employeeClass = employeeClass
    }

    public Employee createNewEmployee(String name, String address, String email, paymentMethod) {
        GenericBuilder employeeBuilder = new GenericBuilder(employeeClass).setName(name)
                                                                          .setAddress(address)
                                                                          .setEmail(email)
                                                                          .setPaymentData(paymentMethod)

        return employeeBuilder.buildAndDoOnSuccess({
            employeeRepository.add(it)
            model.save()
        })
    }
}
