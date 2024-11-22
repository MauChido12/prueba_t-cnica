package com.employeeweb.app.services;


import java.util.Comparator;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeeweb.app.entities.Employee;
import com.employeeweb.app.mapper.Mapper;
import com.employeeweb.app.models.EmployeeDTO;
import com.employeeweb.app.repositories.EmployeeRepository;
import com.employeeweb.app.repositories.JobRepository;
import com.employeeweb.app.utils.Utils;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private JobRepository repositoryJob;

    
    @Override
    public Map<String,List<Employee>> getJobById(Integer jobid) {
        List<Employee> employees = repository.findByJobId(jobid);

        return employees.stream().
                filter(employee -> 1 == employee.getGender().getId()).
                sorted(Comparator.comparing(Employee::getLastname)).
                collect(Collectors.groupingBy(Employee::getLastname));
    }
    
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
