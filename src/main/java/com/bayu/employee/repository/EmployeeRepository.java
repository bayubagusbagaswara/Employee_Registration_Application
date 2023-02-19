package com.bayu.employee.repository;

import com.bayu.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findByNameContainsIgnoreCase(String name);

    Optional<Employee> findByUserId(String id);

}
