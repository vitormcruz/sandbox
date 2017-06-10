package com.vmc.sandbox.payroll.external.presentation.converter

import com.vmc.sandbox.payroll.payment.type.Monthly
import com.vmc.sandbox.payroll.payment.type.PaymentType
import org.reflections.Reflections

class PaymentTypeConverterClassExtensions {

    private static Set<Class> paymentTypes = new Reflections("com.vmc.sandbox.payroll.payment.type").getSubTypesOf(PaymentType)

    public static paramsFromConverter(Monthly monthly, employeeConverter){
        def params = new Object[2]
        params[0] = Monthly
        params[1] = employeeConverter.salary? Integer.valueOf(employeeConverter.salary) : null
        return params
    }
}
