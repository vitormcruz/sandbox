package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.attachment.SalesReceipt
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.executeNamedValidation
import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class Commission extends Monthly{

    private Integer commissionRate

    protected Commission() {    }

    //Should be used by builder only
    protected Commission(Employee employee, Integer aSalary, Integer aCommissionRate) {
        super(employee, aSalary)
        executeNamedValidation("Validate new Hourly Payment", {
            def context = [name:"commissionRate"]
            if (aCommissionRate == null) {
                issueError(this, context, "payroll.employee.commisionpayment.commissionrate.mandatory")
            } else if (aCommissionRate < 1) {
                issueError(this, context, "payroll.employee.commisionpayment.commissionrate.mustbe.positive.integer")
            } else {
                this.@commissionRate = aCommissionRate
            }
        })
    }

    static Commission newPaymentType(Employee employee, Integer salary, Integer commissionRate) {
        return new GenericBuilder(Commission).withEmployee(employee).withSalary(salary).withCommissionRate(commissionRate).build()
    }

    Integer getCommissionRate() {
        return commissionRate
    }

    @Override
    void addPaymentAttachment(PaymentAttachment paymentAttachment) {
        if(!(paymentAttachment instanceof SalesReceipt)){
            issueError(this, [:], "employee.payment.commission.sales.receipt.payment.info.only")
            return
        }

        this.@workEventAttachments.add(paymentAttachment)
    }
}
