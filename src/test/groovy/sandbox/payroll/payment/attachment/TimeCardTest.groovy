package sandbox.payroll.payment.attachment

import org.joda.time.DateTime
import org.junit.Test
import sandbox.validationNotification.ValidationNotificationTestSetup
import sandbox.validationNotification.builder.imp.GenericBuilder

import static junit.framework.TestCase.fail

class TimeCardTest implements ValidationNotificationTestSetup{

    @Test
    def void "Create a time card providing null to required fields"(){
        def timeCardBuilder = new GenericBuilder(TimeCard).withDate(null)
                                                          .withTime(null)
        timeCardBuilder.buildAndDo(
          {fail("Creating a Time Card without required fields should fail.")},
          {assert validationObserver.errors.containsAll("payroll.timecard.date.required",
                                                        "payroll.timecard.hours.required")})

    }

    @Test
    def void "Create a time card providing valid values to required fields"(){
        def timeCardBuilder = new GenericBuilder(TimeCard)
        def expectedDateTime = new DateTime()
        timeCardBuilder.with(expectedDateTime, 10)
        timeCardBuilder.buildAndDo(
          {assert it.date == expectedDateTime
           assert it.hours == 10 },
          {fail("Creating a Time Card with required fields should be successful.")})
    }
}
