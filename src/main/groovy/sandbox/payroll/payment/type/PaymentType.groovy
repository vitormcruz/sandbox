package sandbox.payroll.payment.type

import sandbox.payroll.payment.attachment.PaymentAttachment

interface PaymentType {

    void postPaymentAttachment(PaymentAttachment paymentInfo)

    Set<PaymentAttachment> getPaymentAttachments()
}