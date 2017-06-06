package com.vmc.sandbox.payroll.payment.type

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.PaymentAttachment
import com.vmc.sandbox.payroll.payment.attachment.WorkEvent

import static com.google.gwt.core.shared.impl.InternalPreconditions.checkArgument

abstract class GenericPaymentType implements PaymentType{

    protected Employee employee

    protected Set<? extends WorkEvent> workEventAttachments = new HashSet<WorkEvent>()

    private GenericPaymentType() {}

    public GenericPaymentType(Employee anEmployee){
        checkArgument(anEmployee != null, "Employee must be provided for payment types, but I got it null")
        this.employee = anEmployee
        anEmployee.registerAsPaymentAttachmentHandler(this)
    }

    @Override
    Employee getEmployee() {
        return employee
    }

    @Override
    void postPaymentAttachment(PaymentAttachment paymentAttachment) {
        if(paymentAttachment instanceof WorkEvent){
            addWorkEvent(paymentAttachment)
        }
    }

    void addWorkEvent(WorkEvent workEvent){
        workEventAttachments.add(workEvent)
    }

    public Set<PaymentAttachment> getPaymentAttachments(){
        return new HashSet<PaymentAttachment>(workEventAttachments)
    }

}
