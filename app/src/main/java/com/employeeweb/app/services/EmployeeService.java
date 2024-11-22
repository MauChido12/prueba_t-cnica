package com.employeeweb.app.services;

import java.util.List;


import com.employeeweb.app.entities.Employee;
import com.employeeweb.app.models.EmployeeDTO;


public interface EmployeeService {

    List<Employee> getJobById(Integer jobId);

    Employee save(EmployeeDTO employeeDto);

    
} 