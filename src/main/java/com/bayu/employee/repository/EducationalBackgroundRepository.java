package com.bayu.employee.repository;

import com.bayu.employee.model.EducationalBackground;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalBackgroundRepository extends JpaRepository<EducationalBackground, String> {

    List<EducationalBackground> findAllByEmployeeId(String employeeId, Sort sort);
}
