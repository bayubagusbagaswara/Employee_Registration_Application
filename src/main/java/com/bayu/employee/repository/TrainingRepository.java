package com.bayu.employee.repository;

import com.bayu.employee.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, String> {
}
