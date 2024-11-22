package com.employeeweb.app.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class EmployeeRequestDTO {

    @NotNull
    private List<Integer> employeeId;

    @NotEmpty
    private String startDate;

    @NotEmpty
    private String endDate;

    public List<Integer> getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(List<Integer> employeeId) {
        this.employeeId = employeeId;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    
}
