package com.vmc.sandbox.payroll.payment.attachment

import com.vmc.sandbox.validationNotification.ApplicationValidationNotifier
import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import org.joda.time.DateTime

class SalesReceipt implements PaymentAttachment, BuilderAwareness{

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    //TODO remover
    def Long id
    private DateTime date
    private Integer amount

    SalesReceipt() {
        //Available only for reflection magic
        invalidForBuilder()
    }

    SalesReceipt(DateTime date, Integer amount) {
        this.date = date
        this.amount = amount
        validateRequiredFields()
    }

    public void validateRequiredFields() {
        if (date == null) notifier.issueError("payroll.salesreceipt.date.required")
        if (amount == null) notifier.issueError("payroll.salesreceipt.amount.required")
    }

    DateTime getDate() {
        return date
    }

    Integer getAmount() {
        return amount
    }
}
