package sandbox.payroll.payment

class Monthly extends GenericPaymentStyle {

    private Integer salary;

    protected Monthly() {}

    public Monthly(Integer salary) {
        this.salary = salary
    }

    Integer getSalary() {
        return salary
    }

    @Override
    void postPaymentInfo(PaymentAddendum paymentInfo) {
        notifier.issueError("employee.payment.monthly.do.not.have.additional.payment.info")
    }
}
