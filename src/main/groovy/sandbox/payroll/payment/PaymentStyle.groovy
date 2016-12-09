package sandbox.payroll.payment

interface PaymentStyle {

    void postPaymentInfo(PaymentAddendum paymentInfo)

    Set<PaymentAddendum> getPaymentInfos()
}