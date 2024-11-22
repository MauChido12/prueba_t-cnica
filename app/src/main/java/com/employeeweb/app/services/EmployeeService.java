package com.employeeweb.app.services;

import java.util.List;
import java.util.Map;

import com.employeeweb.app.dto.EmployeeDTO;
import com.employeeweb.app.entities.Employee;


/**
 * Service interface for managing Employee-related operations.
 * Provides methods for saving employees, filtering by job and gender, and querying by date range.
 */
public interface EmployeeService {

    /**
     * Saves an employee using the provided EmployeeDTO.
     *
     * @param employeeDto the data transfer object containing employee details.
     * @return the saved Employee entity.
     */
    Employee save(EmployeeDTO employeeDto);

     /**
     * Retrieves employees filtered by job ID and gender ID.
     *
     * @param jobId    the ID of the job to filter employees.
     * @param genderId the ID of the gender to filter employees.
     * @return a map where the key represents categories (e.g., departments) and 
     *         the value is a list of employees matching the criteria.
     */
    Map<String,List<Employee>> getJobById(Integer jobId,Integer genderId);


    /**
     * Queries employees based on their IDs and a date range.
     *
     * @param employeesId the list of employee IDs to filter.
     * @param startDate   the start date of the range in "yyyy-MM-dd" format.
     * @param finalDate   the end date of the range in "yyyy-MM-dd" format.
     * @return a list of employees matching the IDs and falling within the specified date range.
     */
    List<Employee> queryEmployees(List<Integer> employeesId,String startDate,String finalDate);

    

    
} 