package com.vmc.sandbox.payroll.external.presentation.converter

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.base.Preconditions
import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.type.PaymentType
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder
import org.apache.commons.lang.StringUtils
import org.reflections.Reflections

class EmployeeJsonConverter {

    private static ObjectMapper mapper = new ObjectMapper().configure(MapperFeature.AUTO_DETECT_FIELDS, false)
    private static Set<Class> paymentTypes = new Reflections("com.vmc.sandbox.payroll.payment.type").getSubTypesOf(PaymentType)

    String id
    String name
    String address
    String email
    String paymentType
    Class<PaymentType> paymentTypeClass
    Integer salary
    Integer commissionRate
    Integer hourRate
    Integer rate

    static builderFromJson(String string){
        def employeeConverter = mapper.readValue(string, EmployeeJsonConverter)
        Preconditions.checkArgument(employeeConverter.paymentType != null, "Json formmat is invalid: you must specify a payment type of one of the following alternatives:" +
                                                                            StringUtils.join(paymentTypes.collect {it.getSimpleName()}, ", "))
        return employeeConverter.meToBuilder()

    }

    def GenericBuilder meToBuilder() {
        return new GenericBuilder(Employee).withName(name).withAddress(address).withEmail(email).withPayment(paymentTypeClass.paramsFromConverter(this))
    }

    String getPaymentType() {
        return paymentType
    }

    void setPaymentType(String aPaymentType) {
        paymentType = aPaymentType
        paymentTypeClass = paymentTypes.find { it.getSimpleName().equalsIgnoreCase(aPaymentType) }
        Preconditions.checkArgument(aPaymentType!=null, "Json formmat is invalid: you must specify a payment type of one of the following alternatives:" +
                                                        StringUtils.join(paymentTypes.collect {it.getSimpleName()}, ", "))
    }
}
