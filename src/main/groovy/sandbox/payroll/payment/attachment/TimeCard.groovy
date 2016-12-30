package sandbox.payroll.payment.attachment

import org.joda.time.DateTime
import sandbox.payroll.IdentifiableTrait
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.builder.BuilderAwareness

class TimeCard implements PaymentAttachment, BuilderAwareness, IdentifiableTrait{

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

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

    DateTime getDate() {
        return date
    }

    Integer getHours() {
        return hours
    }
}
