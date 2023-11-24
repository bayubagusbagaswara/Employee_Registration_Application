package com.bayu.employee.repository;

import com.bayu.employee.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<UserInformation, String> {

    Optional<UserInformation> findByFullNameContainsIgnoreCase(String name);

    Optional<UserInformation> findByUserId(String userId);

    @Query(value = "SELECT e FROM Employee e WHERE e.id_user = :userId", nativeQuery = true)
    Optional<UserInformation> getEmployeeByUserId(@Param("userId") String userId);

    // bisa get employee berdasarkan property levelOfEducation, position, name

    // bisa urutkan data siapa yang pertama kali createdAt

}
