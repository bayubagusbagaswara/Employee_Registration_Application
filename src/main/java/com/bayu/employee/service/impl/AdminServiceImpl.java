package com.bayu.employee.service.impl;

import com.bayu.employee.payload.UserDTO;
import com.bayu.employee.payload.admin.EmployeeAdminDTO;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.work.WorkExperienceDTO;
import com.bayu.employee.repository.EducationalBackgroundRepository;
import com.bayu.employee.service.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.bayu.employee.util.StringUtil.formattedInstantToString;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final EmployeeService employeeService;
    private final EducationalBackgroundService educationalBackgroundService;

    private final EducationalBackgroundRepository educationalBackgroundRepository;
    private final TrainingHistoryService trainingHistoryService;
    private final WorkExperienceService workExperienceService;

    public AdminServiceImpl(UserService userService, EmployeeService employeeService, EducationalBackgroundService educationalBackgroundService, EducationalBackgroundRepository educationalBackgroundRepository, TrainingHistoryService trainingHistoryService, WorkExperienceService workExperienceService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.educationalBackgroundService = educationalBackgroundService;
        this.educationalBackgroundRepository = educationalBackgroundRepository;
        this.trainingHistoryService = trainingHistoryService;
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
                        .createdAt(employeeDTO.getCreatedAt())
                        .createdBy(employeeDTO.getCreatedBy())
                        .updatedAt(employeeDTO.getUpdatedAt())
                        .updatedBy(employeeDTO.getUpdatedBy())
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
        return educationalBackgroundRepository.findAllByEmployeeId(employeeId, sort)
                .stream()
                .map(education -> {
                    return EducationDTO.builder()
                            .id(education.getId())
                            .employeeId(education.getUserInformation().getId())
                            .levelOfEducation(education.getLevelOfEducation())
                            .department(education.getDepartment())
                            .collegeName(education.getCollegeName())
                            .graduationYear(String.valueOf(education.getGraduationYear()))
                            .createdAt(formattedInstantToString(education.getCreatedAt()))
                            .createdBy(education.getCreatedBy())
                            .updatedAt(education.getUpdatedAt() == null ? null : formattedInstantToString(education.getUpdatedAt()))
                            .updatedBy(education.getUpdatedBy() == null ? null : education.getUpdatedBy())
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDTO> getAllTrainingsByEmployeeId(String employeeId) {
        return trainingHistoryService.getAllTrainingsByEmployeeId(employeeId);
    }

    @Override
    public List<WorkExperienceDTO> getAllWorkExperiencesByEmployeeId(String employeeId) {
        return workExperienceService.getAllWorkExperiencesByEmployeeId(employeeId);
    }

}
