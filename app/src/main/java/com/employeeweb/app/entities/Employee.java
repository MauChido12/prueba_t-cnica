package com.employeeweb.app.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;
    @Column(name="GENDER_ID")
    private Integer genderId;
    @Column(name="JOB_ID")
    private Integer jobId;
    @Column(name="NAME")
    private String name;
    @Column(name="LAST_NAME")
    private String lastname;
    @Column(name="BIRTHDAY")
    private Date birthDate;


   
    public Employee(){}
    
    public Employee(Integer id, Integer genderId, Integer jobId, String name, String lastname, Date birthDate) {
        this.id = id;
        this.genderId = genderId;
        this.jobId = jobId;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getGenderId() {
        return genderId;
    }
    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }
    public Integer getJobId() {
        return jobId;
    }
    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", genderId=" + genderId + ", jobId=" + jobId + ", name=" + name + ", lastname="
                + lastname + ", birthDate=" + birthDate + "]";
    }

    
    
    

}
