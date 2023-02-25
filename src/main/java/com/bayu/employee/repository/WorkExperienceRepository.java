package com.bayu.employee.repository;

import com.bayu.employee.model.WorkExperience;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, String> {

    List<WorkExperience> findAllByUserId(String userId, Sort sort);
}
