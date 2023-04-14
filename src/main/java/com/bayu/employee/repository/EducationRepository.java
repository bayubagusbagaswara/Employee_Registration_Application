package com.bayu.employee.repository;

import com.bayu.employee.model.EducationBackground;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<EducationBackground, String> {

    List<EducationBackground> findAllByEmployeeId(String employeeId, Sort sort);
}
