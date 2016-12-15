package sandbox.payroll.payment.type

import sandbox.payroll.payment.attachment.PaymentAttachment
import sandbox.payroll.payment.attachment.SalesReceipt

class Commission extends Monthly{

    private Integer commissionRate

    protected Commission() {
    }

    def Commission(Integer salary, Integer commissionRate) {
        super(salary);

        this.commissionRate = commissionRate
    }

    Integer getCommissionRate() {
        return commissionRate
    }

    @Override
    void postPaymentAttachment(PaymentAttachment paymentInfo) {
        if(!paymentInfo instanceof SalesReceipt){
            notifier.issueError("employee.payment.commission.sales.receipt.payment.info.only")
        }

        this.@paymentAttachments.add(paymentInfo)
    }
}
