package com.nikash.springboot.employees.service;

import com.nikash.springboot.employees.entity.Employee;
import com.nikash.springboot.employees.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findEmployeeById(long id);

    void updateEmployee(long id, EmployeeRequest employeeRequest);

    Employee insertEmployee(EmployeeRequest employeeRequest);

    void deleteEmployee(long id);


}
