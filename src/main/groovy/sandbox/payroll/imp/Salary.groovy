package sandbox.payroll.imp

import sandbox.payroll.PaymentMethod

class Salary implements PaymentMethod {

    private Integer salary;

    protected Salary() {}

    public Salary(Integer salary) {
        this.salary = salary
    }

    Integer getSalary() {
        return salary
    }
}
