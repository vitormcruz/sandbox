package sandbox.payroll

import sandbox.payroll.payment.PaymentStyle
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.imp.RequiredValidation

class Employee {

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    private Long id

    def String name
    private RequiredValidation requiredNameValidation = new RequiredValidation("employee.name", "payroll.employee.name.mandatory")

    def String address
    private RequiredValidation requiredAddressValidation = new RequiredValidation("employee.address", "payroll.employee.address.mandatory")

    def String email
    private RequiredValidation requiredEmailValidation = new RequiredValidation("employee.email", "payroll.employee.email.mandatory")

    def PaymentStyle paymentData
    private RequiredValidation requiredPaymentBasicInfoValidation = new RequiredValidation("employee.payment", "payroll.employee.payment.basic.info.mandatory")

    Long getId() {
        return id
    }

    public void setName(String name) {
        requiredNameValidation.set(name, {
            this.@name = name
        })
    }

    public void setAddress(String address) {
        requiredAddressValidation.set(address, {
            this.@address = address
        })
    }

    public void setEmail(String email) {
        requiredEmailValidation.set(email, {
            this.@email = email
        })
    }


    public void setPaymentData(PaymentStyle paymentData) {
        requiredPaymentBasicInfoValidation.set(paymentData, {
            this.@paymentData = paymentData
        })
    }
}
