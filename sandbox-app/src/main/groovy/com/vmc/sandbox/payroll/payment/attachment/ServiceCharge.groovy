package com.vmc.sandbox.payroll.payment.attachment

import com.vmc.sandbox.validationNotification.builder.BuilderAwareness
import org.joda.time.DateTime

import static com.vmc.sandbox.validationNotification.ApplicationValidationNotifier.issueError

class ServiceCharge implements UnionAttachment, BuilderAwareness{

    private DateTime date
    private amount

    private ServiceCharge() {
        //Available only for reflection magic
        invalidForBuilder()
    }

    ServiceCharge(DateTime date, amount) {
        validateConstruction {
            this.date = date
            this.amount = amount
            if (date == null) issueError(this, [:], "payroll.servicecharge.date.required")
            if (amount == null) issueError(this, [:], "payroll.servicecharge.amount.required")
        }
    }

    DateTime getDate() {
        return date
    }

    Integer getAmount() {
        return amount
    }
}
