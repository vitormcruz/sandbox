package sandbox.payroll.payment

interface PaymentType {

    void postPaymentInfo(PaymentAttachment paymentInfo)

    Set<PaymentAttachment> getPaymentInfos()
}