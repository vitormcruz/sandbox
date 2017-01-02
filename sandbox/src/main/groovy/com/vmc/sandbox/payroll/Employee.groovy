package com.vmc.sandbox.payroll

import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.type.PaymentType
import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier
import com.vmc.sandbox.validationNotification.imp.RequiredValidation

class Employee {

    //TODO remover
    def Long id

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    def String name
    private RequiredValidation requiredNameValidation = new RequiredValidation("employee.name", "payroll.employee.name.mandatory")

    def String address
    private RequiredValidation requiredAddressValidation = new RequiredValidation("employee.address", "payroll.employee.address.mandatory")

    def String email
    private RequiredValidation requiredEmailValidation = new RequiredValidation("employee.email", "payroll.employee.email.mandatory")

    def PaymentType paymentType
    private RequiredValidation requiredPaymentTypeValidation = new RequiredValidation("employee.payment", "payroll.employee.payment.type.mandatory")

    public void setName(String name) {
        requiredNameValidation.set(name, { this.@name = name })
    }

    public void setAddress(String address) {
        requiredAddressValidation.set(address, { this.@address = address })
    }

    public void setEmail(String email) {
        requiredEmailValidation.set(email, { this.@email = email })
    }

    public void setPaymentType(PaymentType paymentType) {
        requiredPaymentTypeValidation.set(paymentType, { this.@paymentType = paymentType })
    }

    void postPaymentAttachment(PaymentAttachment paymentAttachment){
        paymentType.postPaymentAttachment(paymentAttachment)
    }
}
