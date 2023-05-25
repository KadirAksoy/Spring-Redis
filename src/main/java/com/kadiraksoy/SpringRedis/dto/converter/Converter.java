package com.kadiraksoy.SpringRedis.dto.converter;


import com.kadiraksoy.SpringRedis.dto.EmployeeDto;
import com.kadiraksoy.SpringRedis.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public EmployeeDto convert(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getPhone(),
                employee.getEmail()
                );
    }

    public Employee dtoConvert(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getPhone(),
                employeeDto.getEmail()
        );
    }
}
