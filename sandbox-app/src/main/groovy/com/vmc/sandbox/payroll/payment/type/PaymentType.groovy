package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.attachment.WorkEvent

interface PaymentType {

    Employee getEmployee()

    void postWorkEvent(WorkEvent workEvent)

    Set<PaymentAttachment> getPaymentAttachments()
}