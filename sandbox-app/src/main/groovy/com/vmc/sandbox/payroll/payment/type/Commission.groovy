package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.attachment.SalesReceipt

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

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
    void postPaymentAttachment(PaymentAttachment paymentAttachment) {
        if(!(paymentAttachment instanceof SalesReceipt)){
            issueError(this, [:], "employee.payment.commission.sales.receipt.payment.info.only")
            return
        }

        this.@paymentAttachments.add(paymentAttachment)
    }
}
