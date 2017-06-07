package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.attachment.UnionCharge
import groovy.test.GroovyAssert
import org.junit.Test

class GenericPaymentTypeTest {

    @Test
    def void "Provide null for employee"(){
        def ex = GroovyAssert.shouldFail {new GenericPaymentTypeForTest(null)}
        assert ex.message == "Employee must be provided for payment types, but I got it null"
    }

    @Test
    def void "Provide a valid employee"(){
        def expectedEmployee = new Employee()
        assert new GenericPaymentTypeForTest(expectedEmployee).employee == expectedEmployee
    }

    @Test
    def void "Adding a payment attachment"(){
        def paymentType = new GenericPaymentTypeForTest(new Employee())
        def paymentAttachmentExpected = [] as PaymentAttachment
        paymentType.postWorkEvent(paymentAttachmentExpected)
        assert paymentType.getPaymentAttachments().contains(paymentAttachmentExpected)
    }

    @Test
    def void "Adding a non payment attachment"(){
        def paymentType = new GenericPaymentTypeForTest([] as Employee)
        def nonPaymentAttachment = [] as UnionCharge
        paymentType.postWorkEvent(nonPaymentAttachment)
        assert !paymentType.getPaymentAttachments().contains(nonPaymentAttachment)
    }

    public class GenericPaymentTypeForTest extends GenericPaymentType{

        GenericPaymentTypeForTest(Employee anEmployee) {
            super(anEmployee)
        }
    }
}
