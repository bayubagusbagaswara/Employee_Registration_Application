package com.bayu.employee.service.impl;

import com.bayu.employee.payload.training.CreateTrainingRequest;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.training.UpdateTrainingRequest;
import com.bayu.employee.repository.TrainingRepository;
import com.bayu.employee.service.TrainingService;
import com.bayu.employee.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserService userService;

    public TrainingServiceImpl(TrainingRepository trainingRepository, UserService userService) {
        this.trainingRepository = trainingRepository;
        this.userService = userService;
    }

    @Override
    public TrainingDTO createTraining(String userId, CreateTrainingRequest createTrainingRequest) {
        return null;
    }

    @Override
    public List<TrainingDTO> getAllTrainingsByUserId(String userId) {
        return null;
    }

    @Override
    public TrainingDTO getTrainingById(String trainingId) {
        return null;
    }

    @Override
    public TrainingDTO updateTraining(String trainingId, UpdateTrainingRequest updateTrainingRequest) {
        return null;
    }

    @Override
    public void deleteTraining(String trainingId) {

    }
}
