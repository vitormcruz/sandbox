package com.vmc.sandbox.payroll.payment.attachment

import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import org.joda.time.DateTime

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class SalesReceipt implements PaymentAttachment, BuilderAwareness{

    private DateTime date
    private amount

    SalesReceipt() {
        //Available only for reflection magic
        invalidForBuilder()
    }

    SalesReceipt(DateTime date, amount) {
        validateConstruction {
            this.date = date
            this.amount = amount
            if (date == null) issueError(this, [:], "payroll.salesreceipt.date.required")
            if (amount == null) issueError(this, [:], "payroll.salesreceipt.amount.required")
        }
    }

    DateTime getDate() {
        return date
    }

    Integer getAmount() {
        return amount
    }
}
