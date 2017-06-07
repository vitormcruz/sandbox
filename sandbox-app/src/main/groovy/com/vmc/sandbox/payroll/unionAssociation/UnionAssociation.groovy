package com.vmc.sandbox.payroll.unionAssociation

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.payment.attachment.UnionCharge
import com.vmc.sandbox.payroll.payment.attachment.WorkEvent

interface UnionAssociation {

    Integer getRate()

    Employee getEmployee()

    Boolean isUnionMember()

    void postWorkEvent(WorkEvent workEvent)

    Collection<UnionCharge> getCharges()
}