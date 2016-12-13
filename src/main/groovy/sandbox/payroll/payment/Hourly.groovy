package sandbox.payroll.payment

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
    void postPaymentInfo(PaymentAttachment paymentInfo) {
        if(!paymentInfo instanceof TimeCard){
            notifier.issueError("employee.payment.hourly.time.card.payment.info.only")
        }

        super.@paymentAttachments.add(paymentInfo)
    }
}
