package com.employeeweb.app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.employeeweb.app.entities.Employee;
import java.util.List;


public interface EmployeeRepository extends CrudRepository<Employee,Long>{

    @Query("select e from Employee e where e.name = ?1 and e.lastname = ?2")
    List<Employee> findByNameAndLastnameEmployees(String name, String lastname);


}
