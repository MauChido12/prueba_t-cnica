package com.employeeweb.app.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeweb.app.entities.Employee;
import com.employeeweb.app.models.EmployeeDTO;
import com.employeeweb.app.services.EmployeeService;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Employee> employeeOptional= service.findByJobId(id);
        if (employeeOptional.isPresent()) {
            return ResponseEntity.ok(employeeOptional.orElseThrow());
            
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody EmployeeDTO employeeDTO) {
        Employee employeeNew = service.save(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeNew);
       
        
    }
    




}
