package com.nikash.springboot.employees.service;


import com.nikash.springboot.employees.dao.EmployeeDao;
import com.nikash.springboot.employees.entity.Employee;
import com.nikash.springboot.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findEmployeeById(long id) {
        return employeeDao.findById(id);
    }

    @Override
    public void updateEmployee(long id, EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(id, employeeRequest);
        employeeDao.save(employee);
    }

    @Override
    public Employee insertEmployee(EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(0, employeeRequest);
        employeeDao.save(employee);
        return employee;
    }


    @Override
    public void deleteEmployee(long id) {
        employeeDao.deleteById(id);
    }

    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest){
        Employee employee = new Employee(id,
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getEmail());
        return employee;
    }
}
