package com.vmc.sandbox.payroll

interface EmployeeRepository extends Collection<Employee>{

    Employee get(id)

    void update(Employee employee)

    /**
     * Find the first a single employee QueryBase qEmployee
     *
     *
     * //                employeeRepository.find { QueryBase query, QEmployee qEmployee ->
     //            query.where(qEmployee.id.eq(employeeId))
     //        }
     *
     * @param closure a closure condition
     * @return
     */
    public Employee find(Closure closure);

    public Set<Employee> findAll(Closure closure)

}