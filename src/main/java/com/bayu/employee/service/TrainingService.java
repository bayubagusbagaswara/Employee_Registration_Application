package com.bayu.employee.service;

import com.bayu.employee.payload.training.CreateTrainingRequest;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.training.UpdateTrainingRequest;

import java.util.List;

public interface TrainingService {

    TrainingDTO createTraining(String userId, CreateTrainingRequest createTrainingRequest);

    List<TrainingDTO> getAllTrainingsByUserId(String userId);

    TrainingDTO getTrainingById(String trainingId);

    TrainingDTO updateTraining(String trainingId, UpdateTrainingRequest updateTrainingRequest);

    void deleteTraining(String trainingId);
}
