package com.employeeweb.app.entities;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_worked_hours")
public class EmployeeWorkedHours {
    
    @Id
    @Column(name="ID")
    private Integer id;

    @Column(name="WORKED_HOURS")
    private Double workedHours;

    @Column(name="WORKED_DATE")
    private Date workedDate;
    
    @ManyToOne
    @JoinColumn(name="EMPLOYEE_ID")
    private Employee employee;

    

    public EmployeeWorkedHours() {
    }

    
    public EmployeeWorkedHours(Integer id, Employee employee, Double workedHours, Date workedDate) {
        this.id = id;
        this.employee = employee;
        this.workedHours = workedHours;
        this.workedDate = workedDate;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployeeId(Employee employee) {
        this.employee = employee;
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
        return "EmployeeWorkedHours [id=" + id + ", employee=" + employee + ", workedHours=" + workedHours
                + ", workedDate=" + workedDate + "]";
    }
    
    

    

}
