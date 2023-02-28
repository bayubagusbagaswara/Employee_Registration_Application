package com.bayu.employee.service.impl;

import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.Training;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.training.CreateTrainingRequest;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.training.UpdateTrainingRequest;
import com.bayu.employee.repository.TrainingRepository;
import com.bayu.employee.service.TrainingService;
import com.bayu.employee.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        User user = userService.findById(userId);

        Training training = new Training();
        training.setTrainingName(createTrainingRequest.getTrainingName().toLowerCase());
        training.setCertificate(createTrainingRequest.getCertificate());
        training.setYear(Integer.valueOf(createTrainingRequest.getYear()));
        training.setUser(user);

        trainingRepository.save(training);

        return mapToTrainingDTO(training);
    }

    @Override
    public List<TrainingDTO> getAllTrainingsByUserId(String userId) {
        User user = userService.findById(userId);
        Sort sort = Sort.by("year").ascending();
        List<Training> trainings = trainingRepository.findAllByUserId(user.getId(), sort);
        return mapToTrainingDTOList(trainings);
    }

    @Override
    public TrainingDTO getTrainingById(String trainingId) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new ResourceNotFoundException("Training not found with id : " + trainingId));
        return mapToTrainingDTO(training);
    }

    @Override
    public TrainingDTO updateTraining(String trainingId, UpdateTrainingRequest updateTrainingRequest) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new ResourceNotFoundException("Training not found with id : " + trainingId));

        if (updateTrainingRequest.getTrainingName() != null) {
            training.setTrainingName(updateTrainingRequest.getTrainingName().toLowerCase());
        }

        if (updateTrainingRequest.getCertificate() != null) {
            training.setCertificate(updateTrainingRequest.getCertificate());
        }

        if (updateTrainingRequest.getYear() != null) {
            training.setYear(Integer.valueOf(updateTrainingRequest.getYear()));
        }

        trainingRepository.save(training);

        return mapToTrainingDTO(training);
    }

    @Override
    public void deleteTraining(String trainingId) {
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new ResourceNotFoundException("Training not found with id : " + trainingId));

        trainingRepository.delete(training);
    }

    private static TrainingDTO mapToTrainingDTO(Training training) {
        return TrainingDTO.builder()
                .id(training.getId())
                .userId(training.getUser().getId())
                .trainingName(capitalizeEachWord(training.getTrainingName()))
                .certificate(training.getCertificate())
                .year(String.valueOf(training.getYear()))
                .build();
    }

    private static List<TrainingDTO> mapToTrainingDTOList(List<Training> trainingList) {
        return trainingList.stream()
                .map(TrainingServiceImpl::mapToTrainingDTO)
                .collect(Collectors.toList());
    }

    private static String capitalizeEachWord(String str) {
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || str.charAt(i - 1) == ' ') {
                word.append(Character.toUpperCase(str.charAt(i)));
            } else {
                word.append(str.charAt(i));
            }
        }
        return word.toString();
    }

}