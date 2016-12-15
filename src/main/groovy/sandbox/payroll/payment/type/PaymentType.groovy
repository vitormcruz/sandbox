package sandbox.payroll.payment.type

import sandbox.payroll.payment.attachment.PaymentAttachment

interface PaymentType {

    void postPaymentAttachment(PaymentAttachment paymentAttachment)

    Set<PaymentAttachment> getPaymentAttachments()
}