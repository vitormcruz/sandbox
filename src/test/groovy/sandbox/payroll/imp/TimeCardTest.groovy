package sandbox.payroll.imp

import org.joda.time.DateTime
import org.junit.Test
import sandbox.payroll.payment.TimeCard
import sandbox.validationNotification.ValidationNotificationTestSetup
import sandbox.validationNotification.builder.imp.GenericBuilder

import static junit.framework.TestCase.fail


class TimeCardTest implements ValidationNotificationTestSetup{

    @Test
    def void "Create a time card providing null to required fields"(){
        def timeCardBuilder = new GenericBuilder(TimeCard).withDate(null)
                                                          .withTime(null)
        timeCardBuilder.buildAndDo(
          {fail("time card creating without required fields should fail.")},
          {assert validationObserver.errors.containsAll("payroll.timecard.date.required",
                                                        "payroll.timecard.hours.required")})

    }

    @Test
    def void "Create a time card providing valid values to required fields"(){
        def timeCardBuilder = new GenericBuilder(TimeCard)
        def expectedDatTime = new DateTime()
        timeCardBuilder.with(expectedDatTime, 10)
        timeCardBuilder.buildAndDo(
          {assert it.date == expectedDatTime
           assert it.hours == 10 },
          {fail("time card creating with required fields should be sucessfull.")})

    }
}
