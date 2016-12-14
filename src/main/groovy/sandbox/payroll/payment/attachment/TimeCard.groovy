package sandbox.payroll.payment.attachment

import org.joda.time.DateTime
import sandbox.validationNotification.ApplicationValidationNotifier
import sandbox.validationNotification.builder.BuilderAwareness

class TimeCard implements PaymentAttachment, BuilderAwareness{

    private static ApplicationValidationNotifier notifier = new ApplicationValidationNotifier()

    private Long id
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

    Long getId() {
        return id
    }

    DateTime getDate() {
        return date
    }

    Integer getHours() {
        return hours
    }
}
