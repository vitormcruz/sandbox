package com.vmc.sandbox.payroll.external.persistence.querydsl.entity

import com.vmc.sandbox.payroll.Employee
import com.vmc.sandbox.payroll.IEmploye

import javax.annotation.Generated
/**
 * EmployeeEntity is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class EmployeeEntity implements IEmploye {

    private Long employeeid;

    @Delegate
    private IEmploye employee = new Employee();

    private String name

    private String address

    private String email

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid
    }

    public String getName() {
        return name
    }

    public void setName(String name) {
        employee.setName(name)
        this.name = employee.getName()
    }

    public void setAddress(String address) {
        employee.setAddress(address)
        this.address = employee.getAddress()
    }

    public void setEmail(String email) {
        employee.setEmail(email)
        this.email = employee.getEmail()
    }

    public void setPayment(String email) {
        employee.setEmail(email)
        this.email = employee.getEmail()
    }

}

