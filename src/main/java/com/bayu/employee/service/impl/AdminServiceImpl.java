package com.bayu.employee.service.impl;

import com.bayu.employee.payload.UserDTO;
import com.bayu.employee.payload.admin.EmployeeAdminDTO;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.work.WorkDTO;
import com.bayu.employee.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final EmployeeService employeeService;
    private final EducationService educationService;
    private final TrainingService trainingService;
    private final WorkExperienceService workExperienceService;

    public AdminServiceImpl(UserService userService, EmployeeService employeeService, EducationService educationService, TrainingService trainingService, WorkExperienceService workExperienceService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.educationService = educationService;
        this.trainingService = trainingService;
        this.workExperienceService = workExperienceService;
    }

    @Override
    public List<EmployeeAdminDTO> getAllEmployees() {
        return employeeService.getAllEmployees().stream()
                .map(employeeDTO -> EmployeeAdminDTO.builder()
                        .position(employeeDTO.getPosition())
                        .nik(employeeDTO.getNik())
                        .fullName(employeeDTO.getFullName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @Override
    public UserDTO getUserById(String userId) {
        return userService.getUserById(userId);
    }

    @Override
    public List<EducationDTO> getAllEducationsByUserId(String employeeId) {
        return educationService.getAllByEmployeeId(employeeId);
    }

    @Override
    public List<TrainingDTO> getAllTrainingsByUserId(String employeeId) {
        return trainingService.getAllTrainingsByEmployeeId(employeeId);
    }

    @Override
    public List<WorkDTO> getAllWorksByUserId(String userId) {
        return workExperienceService.getAllByEmployeeId(userId);
    }

}
