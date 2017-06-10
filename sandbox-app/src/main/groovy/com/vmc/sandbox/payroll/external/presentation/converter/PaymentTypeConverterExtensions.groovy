package com.vmc.sandbox.payroll.external.presentation.converter

import com.vmc.sandbox.payroll.payment.type.Monthly

class PaymentTypeConverterExtensions {

    public static fillConverterWithParams(Monthly monthly, EmployeeJsonConverter employeeConverter){
        employeeConverter.paymentType = Monthly.simpleName
        employeeConverter.salary = monthly.salary
    }
}
