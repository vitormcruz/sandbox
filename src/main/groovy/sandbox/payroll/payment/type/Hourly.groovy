package sandbox.payroll.payment.type

import sandbox.payroll.payment.attachment.PaymentAttachment
import sandbox.payroll.payment.attachment.TimeCard

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
    void postPaymentAttachment(PaymentAttachment paymentInfo) {
        if(!paymentInfo instanceof TimeCard){
            notifier.issueError("employee.payment.hourly.time.card.payment.info.only")
        }

        this.@paymentAttachments.add(paymentInfo)
    }
}
