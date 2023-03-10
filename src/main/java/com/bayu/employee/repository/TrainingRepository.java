package com.bayu.employee.repository;

import com.bayu.employee.model.Training;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, String> {

    List<Training> findAllByEmployeeId(String employeeId, Sort sort);
}
