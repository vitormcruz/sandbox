package sandbox.payroll.imp

class Salary extends GenericPaymentMethod {

    private Integer value;

    protected Salary() {}

    public Salary(Integer value) {
        this.value = value
    }

    Integer getValue() {
        return value
    }
}
