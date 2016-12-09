package sandbox.payroll.payment

import sandbox.validationNotification.ApplicationValidationNotifier

abstract class GenericPaymentStyle implements PaymentStyle{

    protected static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    protected Long id
    protected Set<? extends PaymentAddendum> paymentInfos = new HashSet<PaymentAddendum>()


    public Set<PaymentAddendum> getPaymentInfos(){
        return new HashSet<PaymentAddendum>(paymentInfos)
    }

}
