package sandbox.payroll.imp

import sandbox.payroll.PaymentData
import sandbox.payroll.PaymentInfo
import sandbox.validationNotification.ApplicationValidationNotifier

abstract class GenericPaymentData implements PaymentData{

    protected static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    protected Long id
    protected Set<? extends PaymentInfo> paymentInfos = new HashSet<PaymentInfo>()


    public Set<PaymentInfo> getPaymentInfos(){
        return new HashSet<PaymentInfo>(paymentInfos)
    }

}
