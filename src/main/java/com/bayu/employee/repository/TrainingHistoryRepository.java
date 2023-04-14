package com.bayu.employee.repository;

import com.bayu.employee.model.TrainingHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingHistoryRepository extends JpaRepository<TrainingHistory, String> {

    List<TrainingHistory> findAllByEmployeeId(String employeeId, Sort sort);
}
