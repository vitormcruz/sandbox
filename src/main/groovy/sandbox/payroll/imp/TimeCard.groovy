package sandbox.payroll.imp

import org.joda.time.DateTime
import sandbox.payroll.PaymentInfo

class TimeCard implements PaymentInfo{

    private Long id
    private DateTime date
    private Integer hours

    TimeCard() {
    }

    TimeCard(DateTime date, Integer hours) {
        this.hours = hours
        this.date = date
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
