package com.employeeweb.app.entities;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_worked_hours")
public class EmployeeWorkedHours {
    
    @Id
    @Column(name="ID")
    private Integer id;
    @Column(name="EMPLOYEE_ID")
    private Integer employeeId;
    @Column(name="WORKED_HOURS")
    private Double workedHours;
    @Column(name="WORKED_DATE")
    private Date workedDate;

    

    public EmployeeWorkedHours() {
    }

    
    public EmployeeWorkedHours(Integer id, Integer employeeId, Double workedHours, Date workedDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.workedHours = workedHours;
        this.workedDate = workedDate;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    public Double getWorkedHours() {
        return workedHours;
    }
    public void setWorkedHours(Double workedHours) {
        this.workedHours = workedHours;
    }
    public Date getWorkedDate() {
        return workedDate;
    }
    public void setWorkedDate(Date workedDate) {
        this.workedDate = workedDate;
    }


    @Override
    public String toString() {
        return "EmployeeWorkedHours [id=" + id + ", employeeId=" + employeeId + ", workedHours=" + workedHours
                + ", workedDate=" + workedDate + "]";
    }
    
    

    

}
