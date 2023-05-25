package com.kadiraksoy.SpringRedis.controller;

import com.kadiraksoy.SpringRedis.dto.EmployeeDto;
import com.kadiraksoy.SpringRedis.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/find/all")
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @Cacheable(value = "employees", key = "#id", condition= "#result.get().phone.equals('111-222-4444')")
    @GetMapping("/find/{id}")
    public EmployeeDto findById(@PathVariable Long id) {
        LOG.info("Getting Employee with ID {}.", id);
        return employeeService.findById(id);
    }

    @CachePut(value = "employees", key = "#employee.id")
    @PostMapping("/save")
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto) {
        LOG.info("Saving Employee.");
        employeeService.save(employeeDto);
        return employeeDto;
    }
}
