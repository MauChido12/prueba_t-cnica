package com.employeeweb.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.employeeweb.app.entities.EmployeeWorkedHours;

public interface EmployeeWorkedHoursRepository extends CrudRepository<EmployeeWorkedHours,Long> {

}
