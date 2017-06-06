package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.WorkEvent
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
    def void "Adding a work event attachment"(){
        def paymentType = new GenericPaymentTypeForTest(new Employee())
        def workEventExpected = [] as WorkEvent
        paymentType.postPaymentAttachment(workEventExpected)
        assert paymentType.getPaymentAttachments().contains(workEventExpected)
    }

    @Test
    def void "Adding a non work event attachment"(){
        def expectedEmployee = new Employee()
        assert new GenericPaymentTypeForTest(expectedEmployee).employee == expectedEmployee
    }

    public class GenericPaymentTypeForTest extends GenericPaymentType{

        GenericPaymentTypeForTest(Employee anEmployee) {
            super(anEmployee)
        }
    }
}
