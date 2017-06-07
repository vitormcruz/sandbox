package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import org.junit.Test

class MonthlyBasicValidationUnitTest extends ValidationNotificationTestSetup{

    @Test
    def void "Validate positive Salary"(){
        def monthlySalary = getMonthlyPaymentTypeWith(10)
        assert monthlySalary != null
        assert monthlySalary.getSalary() == 10
    }

    @Test
    def void "Validate negative Salary"(){
        assert getMonthlyPaymentTypeWith(-1) == null
        assert validationObserver.errors.contains("payroll.employee.monthlypayment.salary.mustbe.positive.integer")
    }

    @Test
    def void "Validate zero Salary"(){
        assert getMonthlyPaymentTypeWith(0) == null
        assert validationObserver.errors.contains("payroll.employee.monthlypayment.salary.mustbe.positive.integer")
    }

    @Test
    def void "Provide null to the salary"(){
        assert getMonthlyPaymentTypeWith(null) == null
        assert validationObserver.getErrors().contains("payroll.employee.monthlypayment.salary.mandatory")
    }

    public Monthly getMonthlyPaymentTypeWith(Integer salary) {
        return Monthly.newPaymentType([] as Employee, salary)
    }
}
