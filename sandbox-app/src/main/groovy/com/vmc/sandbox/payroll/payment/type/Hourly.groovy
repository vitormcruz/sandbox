package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.TimeCard
import com.vmc.sandbox.payroll.payment.attachment.WorkEvent
import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.executeNamedValidation
import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class Hourly extends GenericPaymentType implements BuilderAwareness{

    private Integer hourRate

    private Hourly() {
        super()
        //Available only for reflection magic
        invalidForBuilder()
    }

    //Should be used by builder only
    protected Hourly(Employee employee, Integer aHourRate) {
        super(employee)
        executeNamedValidation("Validate new Hourly Payment", {
            def context = [name:"hourRate"]
            if (aHourRate == null) {
                issueError(this, context, "payroll.employee.hourlypayment.hourRate.mandatory")
            } else if (aHourRate < 1) {
                issueError(this, context, "payroll.employee.hourlypayment.hourRate.mustbe.positive.integer")
            } else {
                this.@hourRate = aHourRate
            }
        })
    }

    static Hourly newPaymentType(Employee employee, Integer hourRate) {
        return new GenericBuilder(Hourly).withEmployee(employee).withHourRate(hourRate).build()
    }

    Integer getHourRate() {
        return hourRate
    }

    @Override
    void addWorkEvent(WorkEvent workEvent) {
        if(!(workEvent instanceof TimeCard)){
            issueError(this, [:], "employee.payment.hourly.time.card.payment.info.only")
            return
        }

        this.@workEventAttachments.add(workEvent)
    }
}
