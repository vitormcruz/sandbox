package com.vmc.sandbox.payroll.payment.attachment

import org.joda.time.DateTime
import org.junit.Test
import com.vmc.sandbox.validationNotification.ValidationNotificationTestSetup
import com.vmc.sandbox.validationNotification.builder.imp.GenericBuilder

import static junit.framework.TestCase.fail

class SalesReceiptTest implements ValidationNotificationTestSetup {

    @Test
    def void "Create a sales receipt providing null to required fields"(){
        def salesReceiptBuilder = new GenericBuilder(SalesReceipt).withDate(null)
                                                                  .withAmount(null)
        salesReceiptBuilder.buildAndDo(
          {fail("Creating a Sales Receipt without required fields should fail.")},
          {assert validationObserver.errors.containsAll("payroll.salesreceipt.amount.required",
                                                        "payroll.salesreceipt.date.required")})
    }

    @Test
    def void "Create a sales receipt providing valid values to required fields"(){
        def selesReceipt = new GenericBuilder(SalesReceipt)
        def expectedDateTime = new DateTime()
        selesReceipt.with(expectedDateTime, 10)
        selesReceipt.buildAndDo(
          {assert it.date == expectedDateTime
           assert it.amount == 10 },
          {fail("Creating a Sales Receipt with required fields should be successful.")})

    }

}
