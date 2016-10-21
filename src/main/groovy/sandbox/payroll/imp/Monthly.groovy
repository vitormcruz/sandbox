package sandbox.payroll.imp

class Monthly extends GenericPaymentMethod {

    private Integer salary;

    protected Monthly() {}

    public Monthly(Integer salary) {
        this.salary = salary
    }

    Integer getSalary() {
        return salary
    }
}
