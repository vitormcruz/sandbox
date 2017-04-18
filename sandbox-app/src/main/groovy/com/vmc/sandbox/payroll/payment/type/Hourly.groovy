package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.attachment.TimeCard

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class Hourly extends GenericPaymentType{

    private Integer hourRate

    protected Hourly() {
    }

    def Hourly(Integer hourRate) {
        this.hourRate = hourRate
    }

    Integer getHourRate() {
        return hourRate
    }

    @Override
    void postPaymentAttachment(PaymentAttachment paymentAttachment) {
        if(!paymentAttachment instanceof TimeCard){
            issueError(this, [:], "employee.payment.hourly.time.card.payment.info.only")
        }

        this.@paymentAttachments.add(paymentAttachment)
    }
}
