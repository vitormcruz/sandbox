package sandbox.payroll

import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.MandatorySetterValidation

class EmployeeImp implements Employee{

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    private Long id
    def String name
    private MandatorySetterValidation mandatoryNameValidation = new MandatorySetterValidation("employee.name", "payroll.employee.name.mandatory")

    def String address
    private MandatorySetterValidation mandatoryAddressValidation = new MandatorySetterValidation("employee.address", "payroll.employee.address.mandatory")

    def String email
    private MandatorySetterValidation mandatoryEmailValidation = new MandatorySetterValidation("employee.email", "payroll.employee.email.mandatory")

    def PaymentMethod paymentMethod
    private MandatorySetterValidation mandatoryPaymentMethodValidation = new MandatorySetterValidation("employee.paymentMethod", "payroll.employee.paymentMethod.mandatory")

    protected EmployeeImp() {
        notifier.issueMandatoryObligation("employee.name", "payroll.employee.name.mandatory")
        notifier.issueMandatoryObligation("employee.address", "payroll.employee.address.mandatory")
        notifier.issueMandatoryObligation("employee.email", "payroll.employee.email.mandatory")
        notifier.issueMandatoryObligation("employee.paymentMethod", "payroll.employee.paymentMethod.mandatory")
    }

    @Override
    Long getId() {
        return id
    }

    @Override
    public void setName(String name) {
        if(mandatoryNameValidation.canSet(name)){
            this.name = name
        }
    }

    @Override
    public void setAddress(String address) {
        if(mandatoryAddressValidation.canSet(address)){
            this.address = address
        }
    }

    @Override
    public void setEmail(String email) {
        if(mandatoryEmailValidation.canSet(email)){
            this.email = email
        }
    }

    @Override
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        if(mandatoryPaymentMethodValidation.canSet(paymentMethod)){
            this.paymentMethod = paymentMethod
        }
    }
}
