package com.vmc.sandbox.payroll.payment.attachment

import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder
import org.joda.time.DateTime

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.executeNamedValidation
import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class TimeCard implements WorkEvent, BuilderAwareness{

    private DateTime date
    private Integer hours

    private TimeCard() {
        //Available only for reflection magic
        invalidForBuilder()
    }

    //Should be used by builder only
    protected TimeCard(DateTime date, Integer hours) {
        executeNamedValidation("Validate new TimeCard", {
            date != null ? this.@date = date : issueError(this, [name:"date"], "payroll.timecard.date.required")
            hours != null ? this.@hours = hours : issueError(this, [name:"hours"], "payroll.timecard.hours.required")
        })
    }

    public static TimeCard newTimeCard(DateTime date, Integer hours){
        return new GenericBuilder(TimeCard).withDate(date).withHours(hours).build()
    }

    DateTime getDate() {
        return date
    }

    Integer getHours() {
        return hours
    }
}
