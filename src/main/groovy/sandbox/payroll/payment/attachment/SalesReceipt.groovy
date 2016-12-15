package sandbox.payroll.payment.attachment

import org.joda.time.DateTime
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.builder.BuilderAwareness

class SalesReceipt implements PaymentAttachment, BuilderAwareness{

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    private Long id;
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

    Long getId() {
        return id
    }

    DateTime getDate() {
        return date
    }

    Integer getAmount() {
        return amount
    }
}
