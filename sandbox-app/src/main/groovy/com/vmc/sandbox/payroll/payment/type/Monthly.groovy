package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.executeNamedValidation
import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class Monthly extends GenericPaymentType implements BuilderAwareness{

    protected Integer salary


    protected Monthly() {
        super()
        //Available only for reflection magic
        invalidForBuilder()
    }

    //Should be used by builder only
    protected Monthly(Employee employee, Integer aSalary) {
        super(employee)
        executeNamedValidation("Validate new Monhtly Payment", {
            def context = [name: "salary"]
            if (aSalary == null) {
                issueError(this, context, "payroll.employee.monthlypayment.salary.mandatory")
            } else if (aSalary < 1) {
                issueError(this, context, "payroll.employee.monthlypayment.salary.mustbe.positive.integer")
            } else {
                this.@salary = aSalary
            }
        })
    }

    public static Monthly newPaymentType(Employee employee, Integer salary){
        return new GenericBuilder(Monthly).withEmployee(employee).withSalary(salary).build()
    }

    Integer getSalary() {
        return salary
    }

    @Override
    void postPaymentAttachment(PaymentAttachment paymentAttachment) {
        throw new UnsupportedOperationException("Monthly payment does not have payment attachments")
    }
}
