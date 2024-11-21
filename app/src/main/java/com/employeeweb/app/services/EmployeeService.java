package com.employeeweb.app.services;

import java.util.Optional;

import com.employeeweb.app.entities.Employee;

public interface EmployeeService {

    Optional<Employee> findByJobId(Long id);

    Employee save(Employee employee);

    
} 