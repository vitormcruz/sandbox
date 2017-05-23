package com.vmc.sandbox.payroll.external.persistence.querydsl.entity;

import javax.annotation.Generated;

/**
 * MonthlyEntity is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class MonthlyEntity {

    private Long employeeid;

    private Integer salary;

    public Long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

}

