package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.attachment.SalesReceipt
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class Commission extends Monthly{

    private Integer commissionRate

    protected Commission() {    }

    //Should be used by builder only
    protected Commission(Integer salary, Integer commissionRate) {
        super(salary)
        if(commissionRate == null){ issueError(this, [:], "payroll.employee.commisionpayment.commissionrate.mandatory") }
        else if(commissionRate < 1){ issueError(this, [:], "payroll.employee.commisionpayment.commissionrate.mustbe.positive.integer") }
        else {this.commissionRate = commissionRate}
    }

    static Commission newCommission(Integer salary, Integer commissionRate) {
        return new GenericBuilder(Commission).withSalary(salary).withCommissionRate(commissionRate).build()
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
