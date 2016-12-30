package sandbox.payroll.payment.type

import sandbox.payroll.IdentifiableTrait
import sandbox.payroll.payment.attachment.PaymentAttachment
import sandbox.validationNotification.ApplicationValidationNotifier

abstract class GenericPaymentType implements PaymentType, IdentifiableTrait{

    protected static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    protected Set<? extends PaymentAttachment> paymentAttachments = new HashSet<PaymentAttachment>()

    public Set<PaymentAttachment> getPaymentAttachments(){
        return new HashSet<PaymentAttachment>(paymentAttachments)
    }

}
