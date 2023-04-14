package com.bayu.employee.repository;

import com.bayu.employee.model.TrainingHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<TrainingHistory, String> {

    List<TrainingHistory> findAllByEmployeeId(String employeeId, Sort sort);
}
