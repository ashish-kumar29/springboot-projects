package com.nikash.springboot.employees.dao;

import com.nikash.springboot.employees.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDaoJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // Create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // Execute the Query
        List<Employee> employees = theQuery.getResultList();

        //return the result
        return employees;
    }

    @Override
    public Employee findById(long theId) {
        Employee theEmployee = entityManager.find(Employee.class, theId);
        return theEmployee;
    }

    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        Employee dbEmployee = entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Transactional
    @Override
    public void deleteById(long theId) {
        Employee theEmployee = entityManager.find(Employee.class, theId);
        entityManager.remove(theEmployee);
    }


}

