package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.attachment.TimeCard
import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class Hourly extends GenericPaymentType implements BuilderAwareness{

    private Integer hourRate

    private Hourly() {
        //Available only for reflection magic
        invalidForBuilder()
    }

    //Should be used by builder only
    protected Hourly(Integer hourRate) {
        if(hourRate == null){ issueError(this, [:], "payroll.employee.hourlypayment.hourRate.mandatory") }
        else if(hourRate < 1){ issueError(this, [:], "payroll.employee.hourlypayment.hourRate.mustbe.positive.integer") }
        else {this.hourRate = hourRate}
    }

    static Hourly newHourly(Integer hourRate) {
        return new GenericBuilder(Hourly).withHourRate(hourRate).build()
    }

    Integer getHourRate() {
        return hourRate
    }

    @Override
    void postPaymentAttachment(PaymentAttachment paymentAttachment) {
        if(!(paymentAttachment instanceof TimeCard)){
            issueError(this, [:], "employee.payment.hourly.time.card.payment.info.only")
            return
        }

        this.@paymentAttachments.add(paymentAttachment)
    }
}
