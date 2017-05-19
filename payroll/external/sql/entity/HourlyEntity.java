package payroll.external.sql.entity;

import javax.annotation.Generated;

/**
 * HourlyEntity is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class HourlyEntity {

    private Long employeeid;

    private Integer hourrate;

    public Long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid;
    }

    public Integer getHourrate() {
        return hourrate;
    }

    public void setHourrate(Integer hourrate) {
        this.hourrate = hourrate;
    }

}

