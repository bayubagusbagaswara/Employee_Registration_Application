package com.bayu.employee.repository;

import com.bayu.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findByNameContainsIgnoreCase(String name);

    Optional<Employee> findByUserId(String userId);

    @Query(value = "SELECT e FROM Employee e WHERE e.id_user = :userId", nativeQuery = true)
    Optional<Employee> getEmployeeByUserId(@Param("userId") String userId);

}
