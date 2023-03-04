package com.bayu.employee.service.impl;

import com.bayu.employee.payload.UserDTO;
import com.bayu.employee.payload.admin.EmployeeAdminDTO;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.work.WorkExperienceDTO;
import com.bayu.employee.repository.EducationRepository;
import com.bayu.employee.service.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final EmployeeService employeeService;
    private final EducationService educationService;

    private final EducationRepository educationRepository;
    private final TrainingService trainingService;
    private final WorkExperienceService workExperienceService;

    public AdminServiceImpl(UserService userService, EmployeeService employeeService, EducationService educationService, EducationRepository educationRepository, TrainingService trainingService, WorkExperienceService workExperienceService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.educationService = educationService;
        this.educationRepository = educationRepository;
        this.trainingService = trainingService;
        this.workExperienceService = workExperienceService;
    }

    @Override
    public List<EmployeeAdminDTO> getAllEmployees() {
        return employeeService.getAllEmployees().stream()
                .map(employeeDTO -> EmployeeAdminDTO.builder()
                        .id(employeeDTO.getId())
                        .position(employeeDTO.getPosition())
                        .fullName(employeeDTO.getFullName())
                        .age(employeeDTO.getAge())
                        .gender(employeeDTO.getGender())
                        .levelOfEducation(employeeDTO.getLevelOfEducation())
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
    public List<EducationDTO> getAllEducationsByEmployeeId(String employeeId) {
        Sort sort = Sort.by("graduationYear").ascending();
        return educationRepository.findAllByEmployeeId(employeeId, sort)
                .stream()
                .map(education -> {
                    return EducationDTO.builder()
                            .id(education.getId())
                            .employeeId(education.getEmployee().getId())
                            .levelOfEducation(education.getLevelOfEducation())
                            .department(education.getDepartment())
                            .collegeName(education.getCollegeName())
                            .graduationYear(String.valueOf(education.getGraduationYear()))
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDTO> getAllTrainingsByEmployeeId(String employeeId) {
        return trainingService.getAllTrainingsByEmployeeId(employeeId);
    }

    @Override
    public List<WorkExperienceDTO> getAllWorkExperiencesByEmployeeId(String employeeId) {
        return workExperienceService.getAllWorkExperiencesByEmployeeId(employeeId);
    }

}
