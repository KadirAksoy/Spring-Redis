package com.kadiraksoy.SpringRedis.repository;

import com.kadiraksoy.SpringRedis.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
}
