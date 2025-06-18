package com.nikash.springboot.employees.controller;

import com.nikash.springboot.employees.entity.Employee;
import com.nikash.springboot.employees.request.EmployeeRequest;
import com.nikash.springboot.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Employee Rest API Endpoints", description = "Operations related to Employees.")
@RequestMapping("/api/employees")
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }

    @Operation(summary = "Get Employee with given id", description = "Retrieve a particular employee.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable @Min(value = 1) long id){
        return employeeService.findEmployeeById(id);
    }

    @Operation(summary = "Update an employee", description = "Update an employee with given id.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable @Min(value=1) long id, @Valid @RequestBody EmployeeRequest employeeRequest){
        employeeService.updateEmployee(id, employeeRequest);
    }

    @Operation(summary = "Create an Employee", description = "Add an employee to employees list.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee insertEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        Employee employee = employeeService.insertEmployee(employeeRequest);
        return employee;
    }

    @Operation(summary = "Delete an employee", description = "Delete an employee with given id.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable @Min(value = 1) long id){
        employeeService.deleteEmployee(id);
    }

}
