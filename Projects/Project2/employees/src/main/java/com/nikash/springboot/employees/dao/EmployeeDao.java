package com.nikash.springboot.employees.dao;

import com.nikash.springboot.employees.entity.Employee;
import com.nikash.springboot.employees.service.EmployeeService;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll();

    Employee findById(long theId);

    Employee save(Employee theEmployee);

    void deleteById(long theId);
}
