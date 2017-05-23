package com.vmc.sandbox.payroll

import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.type.PaymentType


interface IEmploye {

    String getName()
    void setName(String name)
    String getAddress()
    void setAddress(String address)
    String getEmail()
    void setEmail(String email)
    PaymentType getPaymentType()
    void setPaymentType(PaymentType paymentType)
    void postPaymentAttachment(PaymentAttachment paymentAttachment)
}