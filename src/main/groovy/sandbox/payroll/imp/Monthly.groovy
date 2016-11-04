package sandbox.payroll.imp

import sandbox.payroll.PaymentInfo

class Monthly extends GenericPaymentData {

    private Integer salary;

    protected Monthly() {}

    public Monthly(Integer salary) {
        this.salary = salary
    }

    Integer getSalary() {
        return salary
    }

    @Override
    void postPaymentInfo(PaymentInfo paymentInfo) {
        notifier.issueError("employee.payment.monthly.do.not.have.additional.payment.info")
    }
}
