package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.attachment.TimeCard
import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.executeNamedValidation
import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class Hourly extends GenericPaymentType implements BuilderAwareness{

    private Integer hourRate

    private Hourly() {
        //Available only for reflection magic
        invalidForBuilder()
    }

    //Should be used by builder only
    protected Hourly(Integer aHourRate) {
        executeNamedValidation("Validate new Hourly Payment", {
            def context = [name:"hourRate"]
            if (aHourRate == null) {
                issueError(this, context, "payroll.employee.hourlypayment.hourRate.mandatory")
            } else if (aHourRate < 1) {
                issueError(this, context, "payroll.employee.hourlypayment.hourRate.mustbe.positive.integer")
            } else {
                this.@hourRate = aHourRate
            }
        })
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
