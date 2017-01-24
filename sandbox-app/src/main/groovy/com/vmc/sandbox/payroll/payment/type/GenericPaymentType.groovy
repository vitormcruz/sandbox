package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier

abstract class GenericPaymentType implements PaymentType{
    //TODO remover
    def Long id

    protected static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    protected Set<? extends PaymentAttachment> paymentAttachments = new HashSet<PaymentAttachment>()

    public Set<PaymentAttachment> getPaymentAttachments(){
        return new HashSet<PaymentAttachment>(paymentAttachments)
    }

}
