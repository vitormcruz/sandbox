package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment

interface PaymentType {

    void postPaymentAttachment(PaymentAttachment paymentAttachment)

    Set<PaymentAttachment> getPaymentAttachments()
}