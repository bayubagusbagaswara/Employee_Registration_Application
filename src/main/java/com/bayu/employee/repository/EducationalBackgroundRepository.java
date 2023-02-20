package com.bayu.employee.repository;

import com.bayu.employee.model.EducationalBackground;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationalBackgroundRepository extends JpaRepository<EducationalBackground, String> {

//    List<EducationalBackground> findAllByEmployeeId(String employeeId);

    List<EducationalBackground> findAllByUserId(String userId);
}
