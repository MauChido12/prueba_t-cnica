package com.employeeweb.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.employeeweb.app.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Long>{


}
