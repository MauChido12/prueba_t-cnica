package com.employeeweb.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="jobs")
public class Job {

    @Id
    @Column(name="ID")
    private Integer id;
    @Column(name="NAME")
    private String name;
    @Column(name="SALARY")
    private Double salary;

    

    public Job() {
    }
    
    public Job(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Job [id=" + id + ", name=" + name + ", salary=" + salary + "]";
    }
    

    

}
