package com.bayu.employee.service.impl;

import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.Employee;
import com.bayu.employee.model.TrainingHistory;
import com.bayu.employee.payload.training.CreateTrainingRequest;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.training.UpdateTrainingRequest;
import com.bayu.employee.repository.TrainingRepository;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.TrainingService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final EmployeeService employeeService;

    public TrainingServiceImpl(TrainingRepository trainingRepository, EmployeeService employeeService) {
        this.trainingRepository = trainingRepository;
        this.employeeService = employeeService;
    }

    @Override
    public TrainingDTO createTraining(String employeeId, CreateTrainingRequest createTrainingRequest) {
        Employee employee = employeeService.findById(employeeId);

        TrainingHistory trainingHistory = new TrainingHistory();
        trainingHistory.setTrainingName(createTrainingRequest.getTrainingName().toLowerCase());
        trainingHistory.setCertificate(createTrainingRequest.getCertificate());
        trainingHistory.setYear(Integer.valueOf(createTrainingRequest.getYear()));
        trainingHistory.setEmployee(employee);

        trainingRepository.save(trainingHistory);

        return mapToTrainingDTO(trainingHistory);
    }

    @Override
    public List<TrainingDTO> getAllTrainingsByEmployeeId(String employeeId) {
        Sort sort = Sort.by("year").ascending();
        List<TrainingHistory> trainingHistories = trainingRepository.findAllByEmployeeId(employeeId, sort);
        return mapToTrainingDTOList(trainingHistories);
    }

    @Override
    public TrainingDTO getTrainingById(String trainingId) {
        TrainingHistory trainingHistory = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new ResourceNotFoundException("Training not found with id : " + trainingId));
        return mapToTrainingDTO(trainingHistory);
    }

    @Override
    public TrainingDTO updateTraining(String trainingId, UpdateTrainingRequest updateTrainingRequest) {
        TrainingHistory trainingHistory = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new ResourceNotFoundException("Training not found with id : " + trainingId));

        if (updateTrainingRequest.getTrainingName() != null) {
            trainingHistory.setTrainingName(updateTrainingRequest.getTrainingName().toLowerCase());
        }

        if (updateTrainingRequest.getCertificate() != null) {
            trainingHistory.setCertificate(updateTrainingRequest.getCertificate());
        }

        if (updateTrainingRequest.getYear() != null) {
            trainingHistory.setYear(Integer.valueOf(updateTrainingRequest.getYear()));
        }

        trainingRepository.save(trainingHistory);

        return mapToTrainingDTO(trainingHistory);
    }

    @Override
    public void deleteTraining(String trainingId) {
        TrainingHistory trainingHistory = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new ResourceNotFoundException("Training not found with id : " + trainingId));

        trainingRepository.delete(trainingHistory);
    }

    private static TrainingDTO mapToTrainingDTO(TrainingHistory trainingHistory) {
        return TrainingDTO.builder()
                .id(trainingHistory.getId())
                .employeeId(trainingHistory.getEmployee().getId())
                .trainingName(capitalizeEachWord(trainingHistory.getTrainingName()))
                .certificate(trainingHistory.getCertificate())
                .year(String.valueOf(trainingHistory.getYear()))
                .build();
    }

    private static List<TrainingDTO> mapToTrainingDTOList(List<TrainingHistory> trainingHistoryList) {
        return trainingHistoryList.stream()
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