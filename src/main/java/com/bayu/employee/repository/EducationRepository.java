package com.bayu.employee.repository;

import com.bayu.employee.model.Education;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, String> {

    List<Education> findAllByEmployeeId(String employeeId, Sort sort);
}
