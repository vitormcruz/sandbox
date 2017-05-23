package com.vmc.sandbox.payroll.external.persistence.querydsl.entity;

import javax.annotation.Generated;

/**
 * CommissionEntity is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class CommissionEntity {

    private Integer commissionrate;

    private Long employeeid;

    public Integer getCommissionrate() {
        return commissionrate;
    }

    public void setCommissionrate(Integer commissionrate) {
        this.commissionrate = commissionrate;
    }

    public Long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid;
    }

}

