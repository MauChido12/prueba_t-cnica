package com.employeeweb.app.services;


import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeeweb.app.dto.EmployeeDTO;
import com.employeeweb.app.entities.Employee;
import com.employeeweb.app.mapper.Mapper;
import com.employeeweb.app.repositories.EmployeeRepository;
import com.employeeweb.app.repositories.JobRepository;
import com.employeeweb.app.utils.Utils;

/**
 * Service implementation for managing Employee-related operations.
 * Implements methods for saving employees, filtering by job and gender, querying employees by IDs and date range, 
 * and performing asynchronous operations.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private JobRepository repositoryJob;

    /**
     * Saves an employee based on the provided EmployeeDTO.
     * First checks if the full name exists, if the person is of legal age, and if the job exists.
     * If any check fails, the employee is not saved.
     *
     * @param employeeDto the data transfer object containing employee details.
     * @return the saved Employee entity, or null if any validation fails.
     */
    @Override
    @Transactional
    public Employee save(EmployeeDTO employeeDto) {
        boolean existFullName = isExistFullName(employeeDto);
        if (!existFullName) {  
            if (Utils.isMajor(employeeDto)) {
                if (existInJob(employeeDto)) {
                    Employee employee = Mapper.convertDTOToTable(employeeDto);
                    return repository.save(employee);
                    
                }
                
            }
        }
        return null;
    }

    /**
     * Retrieves employees based on the job ID and gender ID.
     * Filters the employees by gender and groups them by their last name, sorting them alphabetically.
     *
     * @param jobid    the job ID to filter employees by.
     * @param genderId the gender ID to filter employees by.
     * @return a map where the key is the last name of the employees and the value is a list of employees matching the criteria.
     */
    @Override
    public Map<String,List<Employee>> getJobById(Integer jobid, Integer genderId) {
        List<Employee> employees = repository.findByJobId(jobid);

        return employees.stream().
                filter(employee -> genderId == employee.getGender().getId()).
                sorted(Comparator.comparing(Employee::getLastname)).
                collect(Collectors.groupingBy(Employee::getLastname));
    }

    /**
     * Asynchronously retrieves an employee by their ID.
     * Returns a CompletableFuture, allowing non-blocking retrieval of the employee.
     *
     * @param id the ID of the employee.
     * @return a CompletableFuture containing the Employee entity.
     */
    @Async
    public CompletableFuture<Employee> getEmployeeById(Integer id){
        Employee employee = repository.findById(id);
        return CompletableFuture.completedFuture(employee);
    }

    /**
     * Queries employees based on their IDs and a date range (start and end dates).
     * Filters the employees based on their birth date being within the specified range.
     *
     * @param employeesId a list of employee IDs to query.
     * @param startDate   the start date of the range in "yyyy-MM-dd" format.
     * @param finalDate   the end date of the range in "yyyy-MM-dd" format.
     * @return a list of employees that match the provided IDs and whose birth date falls within the specified range.
     */

    @Override
    public List<Employee> queryEmployees(List<Integer> employeesId, String startDate, String finalDate) {
         Date start =  Utils.getDateFormat("yyyy-MM-dd", startDate);
         Date finalD = Utils.getDateFormat("yyyy-MM-dd",finalDate);

         LocalDate startLocal = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
         LocalDate finalLocal = finalD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

         List<CompletableFuture<Employee>> futureEmployees = new ArrayList<>();

         for (Integer id: employeesId){
            CompletableFuture<Employee> futureEmployee = getEmployeeById(id);
            futureEmployees.add(futureEmployee);
         }

         return futureEmployees.stream()
                .map(CompletableFuture::join)
                .filter(employee -> employee != null &&
                        (employee.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(startLocal) &&
                        employee.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(finalLocal)))
                .collect(Collectors.toList());      




    }
    /**
     * Checks if an employee with the same name and last name already exists in the system.
     *
     * @param employeeDTO the EmployeeDTO to check for existing full name.
     * @return true if the full name already exists, false otherwise.
     */

    private boolean isExistFullName(EmployeeDTO employeeDTO){
        boolean indicator = true;
        if (employeeDTO.getName() != null && employeeDTO.getLastname() != null) {
            String name = employeeDTO.getName();
            String lastname = employeeDTO.getLastname();

            List<Employee> employees = (List<Employee>) repository.findByNameAndLastnameEmployees(name,lastname);

            if (employees.size() > 0) {
                return indicator;
            }
            indicator = false;
                
        }
        return indicator;
    }
    /**
     * Checks if the job ID exists in the job repository.
     *
     * @param employeeDTO the EmployeeDTO containing the job ID.
     * @return true if the job exists, false otherwise.
     */
    private boolean existInJob(EmployeeDTO employeeDTO){
        boolean indicator = false;
        if (employeeDTO.getJobId() != null) {
            Integer idJob = employeeDTO.getJobId();
            List<Integer> listJob = (List<Integer>) repositoryJob.findById(idJob);
            if (listJob.size() > 0) {
                return true;
                
            }
            indicator = false;
        }
        return indicator;

    }

   
}
