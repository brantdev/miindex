package com.mindex.challenge.data;

import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mindex.challenge.data.Employee;
import java.util.Date;

public class Compensation {

    @Id
    private Employee employee;
    private int salary;

    @JsonFormat(pattern="MM-dd-yyyy")
    private Date effectiveDate;

    public Compensation() {
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
