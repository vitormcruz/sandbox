package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment

interface PaymentType {

    Employee getEmployee()

    void postPaymentAttachment(PaymentAttachment paymentAttachment)

    Set<PaymentAttachment> getPaymentAttachments()
}