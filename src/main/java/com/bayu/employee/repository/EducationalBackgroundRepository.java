package com.bayu.employee.repository;

import com.bayu.employee.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationalBackgroundRepository extends JpaRepository<Education, String> {

//    List<EducationalBackground> findAllByEmployeeId(String employeeId);

    List<Education> findAllByUserId(String userId);
}
