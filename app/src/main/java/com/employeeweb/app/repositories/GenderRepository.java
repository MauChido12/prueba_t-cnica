package com.employeeweb.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.employeeweb.app.entities.Gender;

public interface GenderRepository extends CrudRepository<Gender,Long>{

}
