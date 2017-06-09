package com.vmc.sandbox.payroll.external.presentation.converter

import com.vmc.sandbox.payroll.payment.type.Monthly
import com.vmc.sandbox.payroll.payment.type.PaymentType
import org.reflections.Reflections

import static com.google.common.base.Preconditions.checkArgument

class PaymentTypeConverterExtensions {

    private static Set<Class> paymentTypes = new Reflections("com.vmc.sandbox.payroll.payment.type").getSubTypesOf(PaymentType)

    public static paramsFromMap(PaymentType paymentTypeClass, Map map){
        def type = paymentTypes.find { it.getSimpleName().equalsIgnoreCase(map.get("type")) }
        checkArgument(type != null, "The type provided, ${map.get("type")}, don't exist on the system")
        type.paramsFromMap(map)
    }

    public static paramsFromMap(Monthly monthly, Map map){
        def params = new Object[2]
        params[0] = Monthly
        params[1] = Integer.valueOf(map.get("salary"))
        checkArgument(params[1]  != null, "The payment type is Monthly but no salary was provided")
        return params
    }

    public static paramsFromConverter(Monthly monthly, employeeConverter){
        def params = new Object[2]
        params[0] = Monthly
        params[1] = Integer.valueOf(employeeConverter.salary)
        checkArgument(params[1]  != null, "The payment type is Monthly but no salary was provided")
        return params
    }
}
