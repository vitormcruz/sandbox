package sandbox.payroll.payment.attachment

import org.joda.time.DateTime
import sandbox.payroll.IdentifiableTrait
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.builder.BuilderAwareness

class SalesReceipt implements PaymentAttachment, BuilderAwareness, IdentifiableTrait{

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

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
