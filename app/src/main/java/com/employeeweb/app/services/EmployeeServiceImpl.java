package com.employeeweb.app.services;


import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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

    
    @Override
    public Map<String,List<Employee>> getJobById(Integer jobid) {
        List<Employee> employees = repository.findByJobId(jobid);

        return employees.stream().
                filter(employee -> 1 == employee.getGender().getId()).
                sorted(Comparator.comparing(Employee::getLastname)).
                collect(Collectors.groupingBy(Employee::getLastname));
    }

    @Async
    public CompletableFuture<Employee> getEmployeeById(Integer id){
        Employee employee = repository.findById(id);
        return CompletableFuture.completedFuture(employee);
    }

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
