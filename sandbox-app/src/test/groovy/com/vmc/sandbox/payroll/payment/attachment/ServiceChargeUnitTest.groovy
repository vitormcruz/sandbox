package com.vmc.sandbox.payroll.payment.attachment

import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder
import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import org.joda.time.DateTime
import org.junit.Test

import static junit.framework.TestCase.fail

class ServiceChargeUnitTest extends ValidationNotificationTestSetup {

    @Test
    def void "Create a service charge providing null to required fields"(){
        def serviceChargeBuilder = new GenericBuilder(ServiceCharge).withDate(null)
                                                                    .withAmount(null)
        serviceChargeBuilder.buildAndDo(
          {fail("Creating a Service Charge without required fields should fail.")},
          {assert validationObserver.errors.containsAll("payroll.servicecharge.date.required",
                                                        "payroll.servicecharge.amount.required")})

    }

    @Test
    def void "Create a time card providing valid values to required fields"(){
        def serviceChargeBuilder = new GenericBuilder(ServiceCharge)
        def expectedDateTime = new DateTime()
        serviceChargeBuilder.with(expectedDateTime, 10)
        serviceChargeBuilder.buildAndDo(
          {assert it.date == expectedDateTime
           assert it.amount == 10 },
          {fail("Creating a Service Charge with required fields should be successful.")})
    }


}
