package sandbox.payroll.payment

import sandbox.validationNotification.ApplicationValidationNotifier

abstract class GenericPaymentType implements PaymentType{

    protected static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    protected Long id
    protected Set<? extends PaymentAttachment> paymentInfos = new HashSet<PaymentAttachment>()


    public Set<PaymentAttachment> getPaymentInfos(){
        return new HashSet<PaymentAttachment>(paymentInfos)
    }

}
