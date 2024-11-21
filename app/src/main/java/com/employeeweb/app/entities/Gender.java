package com.employeeweb.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="genders")
public class Gender {

    @Id
    @Column(name="ID")
    private Integer id;
    @Column(name="NAME")
    private String name;


    public Gender() {
    }
    
    public Gender(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Gender [id=" + id + ", name=" + name + "]";
    }
    

    

}
