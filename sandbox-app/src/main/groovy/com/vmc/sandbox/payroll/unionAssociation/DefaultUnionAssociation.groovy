package com.vmc.sandbox.payroll.unionAssociation

class DefaultUnionAssociation implements UnionAssociation{

    private Integer rate

    DefaultUnionAssociation(Integer rate) {
        this.rate = rate
    }

    @Override
    Boolean isUnionMember() {
        return true
    }
}
