package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import groovy.test.GroovyAssert
import org.junit.Test

class MonthlyAttachmentUnitTest extends ValidationNotificationTestSetup{

    @Test
    def void "Add a payment attachment to a Monthly payment type"(){
        def ex = GroovyAssert.shouldFail {Monthly.newPaymentType(new Employee(),1).postWorkEvent({} as PaymentAttachment)}
        assert ex.message == "Monthly payment does not have payment attachments"
    }

}
