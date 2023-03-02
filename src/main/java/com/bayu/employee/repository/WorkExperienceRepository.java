package com.bayu.employee.repository;

import com.bayu.employee.model.WorkExperience;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, String> {

    List<WorkExperience> findAllByEmployeeId(String employeeId, Sort sort);

    List<WorkExperience> findAllByEmployeeId(String employeeId);
}
