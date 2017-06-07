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
        anEmployee.registerAsWorkEventHandler(this)
    }

    @Override
    Employee getEmployee() {
        return employee
    }

    @Override
    void postWorkEvent(WorkEvent workEvent) {
        if(workEvent instanceof PaymentAttachment){
            addPaymentAttachment(workEvent)
        }
    }

    void addPaymentAttachment(PaymentAttachment paymentAttachment){
        workEventAttachments.add(paymentAttachment)
    }

    public Set<PaymentAttachment> getPaymentAttachments(){
        return new HashSet<PaymentAttachment>(workEventAttachments)
    }

}
