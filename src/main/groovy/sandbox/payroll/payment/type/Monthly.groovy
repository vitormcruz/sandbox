package sandbox.payroll.payment.type

import sandbox.payroll.payment.attachment.PaymentAttachment

class Monthly extends GenericPaymentType {

    private Integer salary

    protected Monthly() {}

    public Monthly(Integer salary) {
        this.salary = salary
    }

    Integer getSalary() {
        return salary
    }

    @Override
    void postPaymentAttachment(PaymentAttachment paymentInfo) {
        throw new UnsupportedOperationException("Monthly payment does not have payment attachments")
    }
}
