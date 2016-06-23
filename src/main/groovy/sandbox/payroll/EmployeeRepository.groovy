package sandbox.payroll

interface EmployeeRepository extends Collection<EmployeeImp>{

    EmployeeImp get(id)

    void update(EmployeeImp employee)

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
    public EmployeeImp find(Closure closure);


}