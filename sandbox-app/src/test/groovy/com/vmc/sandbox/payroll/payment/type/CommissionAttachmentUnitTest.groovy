package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.payment.attachment.SalesReceipt
import com.vmc.sandbox.payroll.payment.attachment.TimeCard
import com.vmc.sandbox.validationNotification.testPreparation.ValidationNotificationTestSetup
import org.junit.Test

class CommissionAttachmentUnitTest extends ValidationNotificationTestSetup{

    @Test
    def void "Add a sales recipt to a Commission payment type"(){
        def expectedTimeCard = [] as SalesReceipt
        def commission = Commission.newCommission(1, 1)
        commission.postPaymentAttachment(expectedTimeCard)
        assert commission.getPaymentAttachments().contains(expectedTimeCard)
    }

    @Test
    def void "Add another payment attachment to a Commission payment type"(){
        def commission = Commission.newCommission(1, 1)
        commission.postPaymentAttachment([] as TimeCard)
        assert commission.getPaymentAttachments().isEmpty()
        validationObserver.errors.contains("employee.payment.commission.sales.receipt.payment.info.only")
    }
}
