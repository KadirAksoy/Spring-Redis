package com.kadiraksoy.SpringRedis.service;


import com.kadiraksoy.SpringRedis.dto.EmployeeDto;
import com.kadiraksoy.SpringRedis.dto.converter.Converter;
import com.kadiraksoy.SpringRedis.exception.EmployeeExitsException;
import com.kadiraksoy.SpringRedis.model.Employee;
import com.kadiraksoy.SpringRedis.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Converter converter;

    public EmployeeService(EmployeeRepository employeeRepository, Converter converter) {
        this.employeeRepository = employeeRepository;
        this.converter = converter;
    }
    public List<EmployeeDto> findAll() {

        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee: employees) {
            employeeDtos.add(converter.convert(employee));
        }
        return employeeDtos;
    }

    public EmployeeDto findById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.map(converter::convert).orElseThrow();
    }

    public void save(EmployeeDto employeeDto) {
        if (checkIfEmployeeExists(employeeDto.getId())){
            throw new EmployeeExitsException("Employee already exits with id:" + employeeDto.getId());
        }else {
            Employee employee = new Employee();
            employee.setId(employeeDto.getId());
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setPhone(employeeDto.getPhone());
            employee.setEmail(employeeDto.getEmail());

            employeeRepository.save(employee);
        }

    }
    public EmployeeDto update(Long id, EmployeeDto employeeDto){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        optionalEmployee.ifPresent(employee -> {
            employee.setId(employeeDto.getId());
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setPhone(employeeDto.getPhone());
            employee.setEmail(employeeDto.getEmail());
        });
        return employeeDto;

    }
    public void deleteEmployeeByID(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            Employee deletedEmployee = employee.get();
            employeeRepository.delete(deletedEmployee);
        }
    }

    public boolean checkIfEmployeeExists(Long id) {
        return employeeRepository.existsById(id);
    }

}
