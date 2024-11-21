package com.employeeweb.app.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;
    
    @ManyToOne
    @Column(name="GENDER_ID")
    private Gender gender;

    @ManyToOne
    @Column(name="JOB_ID")
    private Job job;

    @Column(name="NAME")
    private String name;

    @Column(name="LAST_NAME")
    private String lastname;

    @Column(name="BIRTHDAY")
    private Date birthDate;


   
    public Employee(){}
    
    public Employee(Integer id, Gender gender, Job job, String name, String lastname, Date birthDate) {
        this.id = id;
        this.gender = gender;
        this.job = job;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
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
        return "Employee [id=" + id + ", gender=" + gender + ", job=" + job + ", name=" + name + ", lastname="
                + lastname + ", birthDate=" + birthDate + "]";
    }


}
