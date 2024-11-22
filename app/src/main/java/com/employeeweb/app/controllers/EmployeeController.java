package com.employeeweb.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PostMapping("/byJob")
    public ResponseEntity<?> getEmployeesByJob(@RequestBody Map<String,Object> request){
        Integer jobId = (Integer) request.get("jobId");
        if (jobId == null) {
            return ResponseEntity.badRequest().build(); // Retorna 400 si falta el jobId
        }
        List<Employee> employeeOptional= service.getJobById(jobId);

        if (employeeOptional.isEmpty()) {
            return ResponseEntity.noContent().build();
            
        }
        return ResponseEntity.ok(employeeOptional);

    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody EmployeeDTO employeeDTO) {
        Employee employeeNew = service.save(employeeDTO);
        Map<String, Object> response = new HashMap<>();
        Integer id = null;
        boolean succes = false;
        response.put("id", id);
        response.put("success", succes);

        if (employeeNew != null) {
            response.put("id",employeeNew.getId());
            response.put("success",true);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
        
       
        
    }
    




}
