package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import org.junit.Test

class HourlyBasicValidationUnitTest extends ValidationNotificationTestSetup{

    @Test
    def void "Validate positive Hour Rate"(){
        def hourRate = Hourly.newPaymentType([] as Employee, 3)
        assert hourRate != null
        assert hourRate.getHourRate() == 3
    }

    @Test
    def void "Validate negative Hour Rate"(){
        assert getHourlyWith(-1) == null
        assert validationObserver.errors.contains("payroll.employee.hourlypayment.hourRate.mustbe.positive.integer")
    }

    @Test
    def void "Validate zero Hour Rate"(){
        assert getHourlyWith(0) == null
        assert validationObserver.errors.contains("payroll.employee.hourlypayment.hourRate.mustbe.positive.integer")
    }

    @Test
    def void "Provide null to the Hour Rate"(){
        assert getHourlyWith(null) == null
        assert validationObserver.getErrors().contains("payroll.employee.hourlypayment.hourRate.mandatory")
    }

    public Hourly getHourlyWith(Integer hourRate) {
        Hourly.newPaymentType([] as Employee, hourRate)
    }
}
