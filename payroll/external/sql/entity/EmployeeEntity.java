package payroll.external.sql.entity;

import javax.annotation.Generated;

/**
 * EmployeeEntity is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class EmployeeEntity {

    private String address;

    private String email;

    private Long employeeid;

    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

