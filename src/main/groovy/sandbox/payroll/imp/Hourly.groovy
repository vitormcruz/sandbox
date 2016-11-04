package sandbox.payroll.imp

import sandbox.payroll.PaymentInfo

class Hourly extends GenericPaymentData{

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
    void postPaymentInfo(PaymentInfo paymentInfo) {
        if(!paymentInfo instanceof TimeCard){
            notifier.issueError("employee.payment.hourly.time.card.payment.info.only")
        }

        super.@paymentInfos.add(paymentInfo)
    }
}
