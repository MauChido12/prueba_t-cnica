package com.employeeweb.app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.employeeweb.app.entities.Employee;


import java.util.List;


/**
 * Repository interface for managing Employee entities.
 * Extends CrudRepository to provide basic CRUD operations along with custom queries.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long>{

    /**
     * Finds employees by their job ID.
     *
     * @param jobId the ID of the job.
     * @return a list of employees associated with the given job ID.
     */
    @Query("select e from Employee e where e.job.id = ?1")
    List<Employee> findByJobId(Integer jobId);


     /**
     * Finds employees by exact match of name and lastname.
     *
     * @param name     the name of the employee.
     * @param lastname the lastname of the employee.
     * @return a list of employees matching the given name and lastname.
     */
    @Query("select e from Employee e where e.name = ?1 and e.lastname = ?2")
    List<Employee> findByNameAndLastnameEmployees(String name, String lastname);

     /**
     * Finds an employee by their ID.
     *
     * @param id the ID of the employee.
     * @return the employee associated with the given ID, or null if not found.
     */
    Employee findById(Integer id);


}
