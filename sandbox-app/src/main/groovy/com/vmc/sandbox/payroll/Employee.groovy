package com.vmc.sandbox.payroll

import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.type.PaymentType
import com.vmc.sandbox.validationNotification.imp.RequiredValidation

class Employee implements Entity{

    private String id = UUID.randomUUID()

    def String name

    private RequiredValidation requiredNameValidation = new RequiredValidation(this, [:], "employee.name", "payroll.employee.name.mandatory")
    def String address

    private RequiredValidation requiredAddressValidation = new RequiredValidation(this, [:], "employee.address", "payroll.employee.address.mandatory")
    def String email

    private RequiredValidation requiredEmailValidation = new RequiredValidation(this, [:], "employee.email", "payroll.employee.email.mandatory")
    def PaymentType paymentType

    private RequiredValidation requiredPaymentTypeValidation = new RequiredValidation(this, [:], "employee.payment", "payroll.employee.payment.type.mandatory")

    @Override
    def getId() {
        return id
    }

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
