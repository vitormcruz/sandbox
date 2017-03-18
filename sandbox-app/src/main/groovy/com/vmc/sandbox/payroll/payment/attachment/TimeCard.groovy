package com.vmc.sandbox.payroll.payment.attachment

import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier
import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import org.joda.time.DateTime

class TimeCard implements PaymentAttachment, BuilderAwareness{

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    private id
    private DateTime date
    private Integer hours

    private TimeCard() {
        //Available only for reflection magic
        invalidForBuilder()
    }

    TimeCard(DateTime date, Integer hours) {
        this.hours = hours
        this.date = date
        validateRequiredFields()
    }

    public void validateRequiredFields() {
        if (date == null) notifier.issueError("payroll.timecard.date.required")
        if (hours == null) notifier.issueError("payroll.timecard.hours.required")
    }

    def getId() {
        return id
    }

    DateTime getDate() {
        return date
    }

    Integer getHours() {
        return hours
    }
}
