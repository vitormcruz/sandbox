package sandbox.payroll.imp

import sandbox.payroll.Employee
import sandbox.payroll.PaymentMethod
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.imp.MandatoryValidation

class EmployeeImp implements Employee{

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    private Long id

    def String name
    private MandatoryValidation mandatoryNameValidation = new MandatoryValidation("employee.name", "payroll.employee.name.mandatory")

    def String address
    private MandatoryValidation mandatoryAddressValidation = new MandatoryValidation("employee.address", "payroll.employee.address.mandatory")

    def String email
    private MandatoryValidation mandatoryEmailValidation = new MandatoryValidation("employee.email", "payroll.employee.email.mandatory")

    def PaymentMethod paymentMethod
    private MandatoryValidation mandatoryPaymentMethodValidation = new MandatoryValidation("employee.paymentMethod", "payroll.employee.paymentMethod.mandatory")

    @Override
    Long getId() {
        return id
    }

    @Override
    public void setName(String name) {
        mandatoryNameValidation.set(name, {
            this.@name = name
        })
    }

    @Override
    public void setAddress(String address) {
        mandatoryAddressValidation.set(address, {
            this.@address = address
        })
    }

    @Override
    public void setEmail(String email) {
        mandatoryEmailValidation.set(email, {
            this.@email = email
        })
    }

    @Override
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        mandatoryPaymentMethodValidation.set(paymentMethod, {
            this.@paymentMethod = paymentMethod
        })
    }
}
