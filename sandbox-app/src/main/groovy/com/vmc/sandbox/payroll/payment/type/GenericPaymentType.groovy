package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment

abstract class GenericPaymentType implements PaymentType{

    private id

    protected Set<? extends PaymentAttachment> paymentAttachments = new HashSet<PaymentAttachment>()

    protected Employee employee

    @Override
    Employee getEmployee() {
        return employee
    }

    public Set<PaymentAttachment> getPaymentAttachments(){
        return new HashSet<PaymentAttachment>(paymentAttachments)
    }

}
