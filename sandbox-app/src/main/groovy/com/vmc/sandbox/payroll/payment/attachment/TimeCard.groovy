package com.vmc.sandbox.payroll.payment.attachment

import com.vmc.sandbox.validationNotification.SimpleValidationObserver
import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import com.vmc.sandbox.validationNotification.builder.ConstructionValidationFailedException
import org.apache.commons.lang.StringUtils
import org.joda.time.DateTime

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.getSimpleObserver
import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class TimeCard implements PaymentAttachment, BuilderAwareness{

    private id
    private DateTime date
    private Integer hours

    private TimeCard() {
        //Available only for reflection magic
        invalidForBuilder()
    }

    TimeCard(DateTime date, Integer hours) {
        SimpleValidationObserver simpleValidationObserver = getSimpleObserver()
        this.hours = hours
        this.date = date
        validateRequiredFields()
        if(!simpleValidationObserver.successful()) throw new ConstructionValidationFailedException(StringUtils.join(simpleValidationObserver.errors, ", "))
    }

    public void validateRequiredFields() {
        if (date == null) issueError(this, [:], "payroll.timecard.date.required")
        if (hours == null) issueError(this, [:], "payroll.timecard.hours.required")
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
