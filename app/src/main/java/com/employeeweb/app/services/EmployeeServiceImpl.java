package com.employeeweb.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employeeweb.app.entities.Employee;
import com.employeeweb.app.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository repository;

    
    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findByJobId(Long id) {
        return repository.findById(id);
    }

    
    @Override
    @Transactional
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

}
