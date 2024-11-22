package com.employeeweb.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import com.employeeweb.app.dto.EmployeeDTO;


public class Utils {

    

     public static Date getDateFormat(String formatPattern, String date){
        SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            return null;
        }

    }

    public static Boolean isMajor(EmployeeDTO employeeDTO){
        Boolean indicator = false;
        if (employeeDTO.getBirthDate() != null) {
            Date fechaNacimiento = getDateFormat("yyyy-MM-dd", employeeDTO.getBirthDate());
            LocalDate fechaNacimientoLocal = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaActual = LocalDate.now();
            Period period = Period.between(fechaNacimientoLocal, fechaActual);
            if (period.getYears() > 18) {
                indicator = true;   
            }
            
        }
        return indicator;
    }

   

}
