package com.employeeweb.app.services;

import java.util.Optional;

import com.employeeweb.app.entities.Employee;
import com.employeeweb.app.models.EmployeeDTO;

public interface EmployeeService {

    Optional<Employee> findByJobId(Long id);

    Employee save(EmployeeDTO employeeDto);

    
} 