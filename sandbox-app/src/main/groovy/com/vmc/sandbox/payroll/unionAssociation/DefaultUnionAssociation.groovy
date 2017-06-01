package com.vmc.sandbox.payroll.unionAssociation

class DefaultUnionAssociation implements UnionAssociation{

    private rate

    DefaultUnionAssociation(rate) {
        this.rate = rate
    }

    @Override
    Boolean isUnionMember() {
        return true
    }
}
