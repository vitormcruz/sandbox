package sandbox.payroll

interface PaymentData {

    void postPaymentInfo(PaymentInfo paymentInfo)

    Set<PaymentInfo> getPaymentInfos()
}