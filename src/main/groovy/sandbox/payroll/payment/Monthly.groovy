package sandbox.payroll.payment

class Monthly extends GenericPaymentType {

    private Integer salary;

    protected Monthly() {}

    public Monthly(Integer salary) {
        this.salary = salary
    }

    Integer getSalary() {
        return salary
    }

    @Override
    void postPaymentInfo(PaymentAttachment paymentInfo) {
        notifier.issueError("employee.payment.monthly.do.not.have.additional.payment.info")
    }
}
