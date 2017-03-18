package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier

abstract class GenericPaymentType implements PaymentType{

    protected static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()
    protected Set<? extends PaymentAttachment> paymentAttachments = new HashSet<PaymentAttachment>()

    private id

    protected Employee employee

    @Override
    Employee getEmployee() {
        return employee
    }

    public Set<PaymentAttachment> getPaymentAttachments(){
        return new HashSet<PaymentAttachment>(paymentAttachments)
    }

}
