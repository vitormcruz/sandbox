package sandbox.payroll.imp

import sandbox.payroll.Employee
import sandbox.payroll.PaymentData
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.imp.RequiredValidation

class EmployeeImp implements Employee{

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    private Long id

    def String name
    private RequiredValidation requiredNameValidation = new RequiredValidation("employee.name", "payroll.employee.name.mandatory")

    def String address
    private RequiredValidation requiredAddressValidation = new RequiredValidation("employee.address", "payroll.employee.address.mandatory")

    def String email
    private RequiredValidation requiredEmailValidation = new RequiredValidation("employee.email", "payroll.employee.email.mandatory")

    def PaymentData paymentData
    private RequiredValidation requiredPaymentBasicInfoValidation = new RequiredValidation("employee.payment", "payroll.employee.payment.basic.info.mandatory")

    @Override
    Long getId() {
        return id
    }

    @Override
    public void setName(String name) {
        requiredNameValidation.set(name, {
            this.@name = name
        })
    }

    @Override
    public void setAddress(String address) {
        requiredAddressValidation.set(address, {
            this.@address = address
        })
    }

    @Override
    public void setEmail(String email) {
        requiredEmailValidation.set(email, {
            this.@email = email
        })
    }


    public void setPaymentData(PaymentData paymentData) {
        requiredPaymentBasicInfoValidation.set(paymentData, {
            this.@paymentData = paymentData
        })
    }
}
