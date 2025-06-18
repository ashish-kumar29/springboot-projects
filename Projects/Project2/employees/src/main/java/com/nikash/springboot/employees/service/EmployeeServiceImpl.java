package com.nikash.springboot.employees.service;


import com.nikash.springboot.employees.dao.EmployeeRepository;
import com.nikash.springboot.employees.entity.Employee;
import com.nikash.springboot.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(long id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee = null;
        if(result.isPresent()){
            employee = result.get();
        }
        else{
            throw new RuntimeException("Didn't find employee with id: "+id);
        }
        return employee;
    }

    @Transactional
    @Override
    public void updateEmployee(long id, EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(id, employeeRequest);
        employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public Employee insertEmployee(EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(0, employeeRequest);
        employeeRepository.save(employee);
        return employee;
    }


    @Transactional
    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest){
        Employee employee = new Employee(id,
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getEmail());
        return employee;
    }
}
