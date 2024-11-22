package com.employeeweb.app.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeweb.app.dto.EmployeeDTO;
import com.employeeweb.app.dto.EmployeeFilterDTO;
import com.employeeweb.app.dto.EmployeeRequestDTO;
import com.employeeweb.app.entities.Employee;
import com.employeeweb.app.services.EmployeeService;
import com.employeeweb.app.utils.Utils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /**
    * Creates a new employee.
    *
    * @param employeeDTO the employee data to be created, validated.
    * @param result      the binding result for validation errors.
    * @return a ResponseEntity containing:
    *         - HTTP 201 (Created) and the ID of the created employee if successful.
    *         - HTTP 400 (Bad Request) if validation errors occur.
    *         - HTTP 500 (Internal Server Error) if an unexpected error occurs.
    */
@PostMapping
@ApiOperation(value = "Create a new employee", notes = "Creates a new employee using the provided data.")
@ApiResponses({
    @ApiResponse(code = 201, message = "Employee created successfully"),
    @ApiResponse(code = 400, message = "Validation error in the request body"),
    @ApiResponse(code = 500, message = "Unexpected server error")
})
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody EmployeeDTO employeeDTO, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasFieldErrors()) {
            response.put("id", null);
            response.put("success", false);
            response.put("errors", result.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            
        }
        
        try {
            Employee employeeNew = service.save(employeeDTO);
            
            response.put("id",employeeNew.getId());
            response.put("success",true);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            response.put("id", null);
            response.put("success", false);
            response.put("error", "An error occurred while saving the employee.");
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        
    }

    /**
 * Retrieves employees based on job ID and gender ID.
 *
 * @param request the filter criteria (job ID and gender ID), validated.
 * @param result  the binding result for validation errors.
 * @return a ResponseEntity containing:
 *         - HTTP 200 (OK) and a list of employees if found.
 *         - HTTP 400 (Bad Request) if validation errors occur.
 *         - HTTP 404 (Not Found) if no employees match the filters.
 *         - HTTP 500 (Internal Server Error) if an unexpected error occurs.
 */
@PostMapping("/byJob")
@ApiOperation(value = "Get employees by job and gender", notes = "Retrieves employees filtered by job ID and gender ID.")
@ApiResponses({
    @ApiResponse(code = 200, message = "Employees retrieved successfully"),
    @ApiResponse(code = 400, message = "Validation error in the request body"),
    @ApiResponse(code = 404, message = "No employees found for the given criteria"),
    @ApiResponse(code = 500, message = "Unexpected server error")
})

    public ResponseEntity<Map<String, Object>> getEmployeesByJob(@Valid @RequestBody EmployeeFilterDTO request,BindingResult result){
        
        Map<String,Object> response = new HashMap<>();
        if (result.hasFieldErrors()) {
            response.put("Employees", new ArrayList<>());
            response.put("success", false);
            response.put("errors", result.getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            
        }
        try {
            
            Integer jobId = request.getJobId();
            Integer genderId = request.getGenderId();
            Map<String,List<Employee>> employeeOptional= service.getJobById(jobId,genderId);
            if (employeeOptional.isEmpty()) {
                response.put("Employees", new ArrayList<>());
                response.put("success", false);
                response.put("message", "No employees found for the given filters.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            response.put("Employees", employeeOptional);
            response.put("success", true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
            
        } catch (Exception e) {
            response.put("employees", new ArrayList<>());
            response.put("success", false);
            response.put("error", "An error occurred while retrieving employees.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            
        }



    }

    /**
    * Queries employees based on ID and date range.
    *
    * @param employeeRequestDTO the request containing employee ID, start date, and end date, validated.
    * @param result             the binding result for validation errors.
    * @return a ResponseEntity containing:
    *         - HTTP 200 (OK) and a list of employees if found.
    *         - HTTP 400 (Bad Request) if validation errors occur or start date is after end date.
    *         - HTTP 404 (Not Found) if no employees match the criteria.
    *         - HTTP 500 (Internal Server Error) if an unexpected error occurs.
    */
    @PostMapping("/queryEmployees")
    @ApiOperation(value = "Query employees by ID and date range", notes = "Retrieves employees filtered by ID and a date range.")
    @ApiResponses({
    @ApiResponse(code = 200, message = "Employees retrieved successfully"),
    @ApiResponse(code = 400, message = "Validation error in the request body or invalid date range"),
    @ApiResponse(code = 404, message = "No employees found for the given criteria"),
    @ApiResponse(code = 500, message = "Unexpected server error")
    })
    public ResponseEntity<Map<String, Object>> queryEmployees(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        // Manejo de errores de validación
        if (result.hasErrors()) {
            response.put("employees", new ArrayList<>());
            response.put("success", false);
            response.put("errors", result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    
        // Validación de fechas
        Date startDate = Utils.getDateFormat("yyyy-MM-dd", employeeRequestDTO.getStartDate());
        Date endDate = Utils.getDateFormat("yyyy-MM-dd", employeeRequestDTO.getEndDate());
        if (startDate.after(endDate)) {
            response.put("employees", new ArrayList<>());
            response.put("success", false);
            response.put("error", "Start date cannot be after end date.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    
        try {
            // Consultar empleados
            List<Employee> employees = service.queryEmployees(
                    employeeRequestDTO.getEmployeeId(),
                    employeeRequestDTO.getStartDate(),
                    employeeRequestDTO.getEndDate()
            );
    
            if (employees.isEmpty()) {
                response.put("employees", new ArrayList<>());
                response.put("success", false);
                response.put("message", "No employees found for the given criteria.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
    
            response.put("employees", employees);
            response.put("success", true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    
        } catch (Exception e) {
            // Manejo de excepciones
            response.put("employees", new ArrayList<>());
            response.put("success", false);
            response.put("error", "An error occurred while querying employees.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
