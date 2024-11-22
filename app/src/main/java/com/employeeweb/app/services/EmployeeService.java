package com.employeeweb.app.services;

import java.util.List;
import java.util.Map;

import com.employeeweb.app.entities.Employee;
import com.employeeweb.app.models.EmployeeDTO;


public interface EmployeeService {

    Employee save(EmployeeDTO employeeDto);

    Map<String,List<Employee>> getJobById(Integer jobId);

    List<Employee> queryEmployees(List<Integer> employeesId,String startDate,String finalDate);

    

    
} 