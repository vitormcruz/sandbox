package com.vmc.sandbox.payroll.payment.attachment

import com.vmc.sandbox.validationNotification.SimpleValidationObserver
import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import com.vmc.sandbox.validationNotification.builder.ConstructionValidationFailedException
import org.apache.commons.lang.StringUtils
import org.joda.time.DateTime

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.getSimpleObserver
import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class SalesReceipt implements PaymentAttachment, BuilderAwareness{


    private id
    private DateTime date
    private amount

    SalesReceipt() {
        //Available only for reflection magic
        invalidForBuilder()
    }

    SalesReceipt(DateTime date, amount) {
        SimpleValidationObserver simpleValidationObserver = getSimpleObserver()
        this.date = date
        this.amount = amount
        validateRequiredFields()
        if(!simpleValidationObserver.successful()) throw new ConstructionValidationFailedException(StringUtils.join(simpleValidationObserver.errors, ", "))
    }

    public void validateRequiredFields() {
        if (date == null) issueError(this, [:], "payroll.salesreceipt.date.required")
        if (amount == null) issueError(this, [:], "payroll.salesreceipt.amount.required")
    }

    def getId() {
        return id
    }

    DateTime getDate() {
        return date
    }

    Integer getAmount() {
        return amount
    }
}
