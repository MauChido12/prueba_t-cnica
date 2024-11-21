package com.employeeweb.app.mapper;

import java.util.Date;

import com.employeeweb.app.entities.Employee;
import com.employeeweb.app.entities.Gender;
import com.employeeweb.app.entities.Job;
import com.employeeweb.app.models.EmployeeDTO;
import com.employeeweb.app.utils.Utils;

public class Mapper {

    public static Employee convertDTOToTable(EmployeeDTO employeeDTO){

        Gender gender = new Gender();
        gender.setId(employeeDTO.getGenderId());
        Job job = new Job();
        job.setId(employeeDTO.getJobId());
        String birth = (String) employeeDTO.getBirthDate();
        Date birthday = Utils.getDateFormat("yyyy-MM-dd",birth);
        Employee employee = new Employee();
        employee.setGender(gender);
        employee.setJob(job);
        employee.setName(employeeDTO.getName());
        employee.setLastname(employeeDTO.getLastname());
        employee.setBirthDate(birthday);
        return employee;
    }

}
