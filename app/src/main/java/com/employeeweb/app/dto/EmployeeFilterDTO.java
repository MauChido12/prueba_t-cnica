package com.employeeweb.app.dto;


import jakarta.validation.constraints.NotNull;

public class EmployeeFilterDTO {

    @NotNull
    private Integer jobId;

    @NotNull
    private Integer genderId;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    

}
