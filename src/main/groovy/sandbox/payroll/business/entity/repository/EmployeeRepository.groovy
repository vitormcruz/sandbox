package sandbox.payroll.business.entity.repository

import sandbox.payroll.business.entity.Employee

interface EmployeeRepository extends Collection<Employee>{

    void update(Employee employee)

    /**
     * Find the first a single employee QueryBase qEmployee
     * @param closure a closure condition
     * @return
     */
    public Employee find(Closure closure);


}