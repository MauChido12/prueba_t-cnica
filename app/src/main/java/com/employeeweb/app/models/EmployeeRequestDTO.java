package com.employeeweb.app.models;

import java.util.List;

public class EmployeeRequestDTO {

    private List<Integer> employeeId;
    private String startDate;
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
