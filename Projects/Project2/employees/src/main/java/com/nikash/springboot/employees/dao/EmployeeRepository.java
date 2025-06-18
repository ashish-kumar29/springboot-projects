package com.nikash.springboot.employees.dao;

import com.nikash.springboot.employees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // That's it ...... No need to write any code
}
