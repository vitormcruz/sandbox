package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class Monthly extends GenericPaymentType implements BuilderAwareness{

    private Integer salary


    protected Monthly() {
        //Available only for reflection magic
        invalidForBuilder()
    }

    //Should be used by builder only
    protected Monthly(Integer salary) {
        if(salary == null){ issueError(this, [:], "payroll.employee.monthlypayment.salary.mandatory") }
        else if(salary < 1){ issueError(this, [:], "payroll.employee.monthlypayment.salary.mustbe.positive.integer") }
        else {this.salary = salary}
    }

    public static Monthly newMonthly(Integer salary){
        return new GenericBuilder(Monthly).withSalary(salary).build()
    }

    Integer getSalary() {
        return salary
    }

    @Override
    void postPaymentAttachment(PaymentAttachment paymentAttachment) {
        throw new UnsupportedOperationException("Monthly payment does not have payment attachments")
    }
}
