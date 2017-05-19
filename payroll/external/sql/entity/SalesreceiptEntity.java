package payroll.external.sql.entity;

import javax.annotation.Generated;

/**
 * SalesreceiptEntity is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class SalesreceiptEntity {

    private Integer amount;

    private java.sql.Timestamp date;

    private Long employeeid;

    private Long id;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

