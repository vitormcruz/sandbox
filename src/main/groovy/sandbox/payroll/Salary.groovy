package sandbox.payroll

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
