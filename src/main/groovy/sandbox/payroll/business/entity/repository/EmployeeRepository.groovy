package sandbox.payroll.business.entity.repository

import sandbox.payroll.business.entity.Employee

interface EmployeeRepository extends Collection<Employee>{

    void update(Employee employee)
}