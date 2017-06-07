package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import org.junit.Test

class CommissionBasicValidationUnitTest extends MonthlyBasicValidationUnitTest{

    @Test
    def void "Validate positive Commission Rate"(){
        def commision = getCommissionPaymentTypeWith(1, 500)
        assert commision != null
        assert commision.getCommissionRate() == 500
    }

    @Test
    def void "Validate negative Commission Rate"(){
        assert getCommissionPaymentTypeWith(1, -1) == null
        assert validationObserver.errors.contains("payroll.employee.commisionpayment.commissionrate.mustbe.positive.integer")
    }

    @Test
    def void "Validate zero Commission Rate"(){
        assert getCommissionPaymentTypeWith(1, 0) == null
        assert validationObserver.errors.contains("payroll.employee.commisionpayment.commissionrate.mustbe.positive.integer")
    }

    @Test
    def void "Provide null to the Commission Rate"(){
        assert getCommissionPaymentTypeWith(1, null) == null
        assert validationObserver.getErrors().contains("payroll.employee.commisionpayment.commissionrate.mandatory")
    }

    Commission getCommissionPaymentTypeWith(Integer salary, Integer rate) {
        return Commission.newPaymentType([] as Employee, salary, rate)
    }

    @Override
    Monthly getMonthlyPaymentTypeWith(Integer salary) {
        return Commission.newPaymentType([] as Employee, salary, 500)
    }
}
