package com.vmc.sandbox.payroll.external.persistence.querydsl.entity;

import javax.annotation.Generated;

/**
 * TimeCardEntity is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class TimeCardEntity {

    private java.sql.Timestamp date;

    private Long employeeid;

    private Integer hours;

    private Long id;

    public java.sql.Timestamp getDate() {
        return date;
    }

    public void setDate(java.sql.Timestamp date) {
        this.date = date;
    }

    public Long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

