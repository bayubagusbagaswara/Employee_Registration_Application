package com.bayu.employee.service.impl;

import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.Employee;
import com.bayu.employee.model.WorkExperience;
import com.bayu.employee.payload.work.CreateWorkExperienceRequest;
import com.bayu.employee.payload.work.UpdateWorkExperienceRequest;
import com.bayu.employee.payload.work.WorkExperienceDTO;
import com.bayu.employee.repository.WorkExperienceRepository;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.WorkExperienceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.bayu.employee.util.StringUtil.formattedInstantToString;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final EmployeeService employeeService;

    public WorkExperienceServiceImpl(WorkExperienceRepository workExperienceRepository, EmployeeService employeeService) {
        this.workExperienceRepository = workExperienceRepository;
        this.employeeService = employeeService;
    }

    @Override
    public WorkExperienceDTO createWorkExperience(String employeeId, CreateWorkExperienceRequest createWorkExperienceRequest) {
        Employee employee = employeeService.findById(employeeId);

        WorkExperience workExperience = WorkExperience.builder()
                .position(createWorkExperienceRequest.getPosition())
                .companyName(createWorkExperienceRequest.getCompanyName())
                .salary(formatToDecimal(createWorkExperienceRequest.getSalary()))
                .yearOfEmployment(createWorkExperienceRequest.getYearOfEmployment())
                .yearOfResignation(createWorkExperienceRequest.getYearOfResignation())
                .employee(employee)
                .build();

        workExperience.setCreatedAt(Instant.now());
        workExperience.setCreatedBy("SYSTEM");

        workExperienceRepository.save(workExperience);

        return mapToWorkDTO(workExperience);
    }

    @Override
    public List<WorkExperienceDTO> getAllWorkExperiencesByEmployeeId(String employeeId) {
        List<WorkExperience> workExperienceList = workExperienceRepository.findAllByEmployeeId(employeeId);
        return mapToWorkDTOList(workExperienceList);
    }

    @Override
    public WorkExperienceDTO getWorkExperienceById(String workId) {
        WorkExperience workExperience = workExperienceRepository.findById(workId).orElseThrow(() -> new ResourceNotFoundException("Work Experience not found with id : " + workId));
        return mapToWorkDTO(workExperience);
    }

    @Override
    public WorkExperienceDTO updateWorkExperience(String workExperienceId, UpdateWorkExperienceRequest updateWorkExperienceRequest) {
        WorkExperience workExperience = workExperienceRepository.findById(workExperienceId)
                .orElseThrow(() -> new RuntimeException("Work Experience not found with id : [" + workExperienceId + "]"));

        workExperience.setPosition(updateWorkExperienceRequest.getPosition());
        workExperience.setCompanyName(updateWorkExperienceRequest.getCompanyName());
        workExperience.setSalary(new BigDecimal(updateWorkExperienceRequest.getSalary()));
        workExperience.setYearOfEmployment(LocalDateTime.parse(updateWorkExperienceRequest.getYearOfEmployment()));
        workExperience.setYearOfResignation(LocalDateTime.parse(updateWorkExperienceRequest.getYearOfResignation()));

        workExperience.setUpdatedAt(Instant.now());
        workExperience.setUpdatedBy("SYSTEM");

        workExperienceRepository.save(workExperience);

        return mapToWorkDTO(workExperience);
    }

    @Override
    public void deleteWorkExperience(String workExperienceId) {
        WorkExperience workExperience = workExperienceRepository.findById(workExperienceId)
                .orElseThrow(() -> new RuntimeException("Work Experience not found with id : [" + workExperienceId + "]"));

        workExperience.setDeleted(true);
        workExperienceRepository.save(workExperience);
    }

    private static WorkExperienceDTO mapToWorkDTO(WorkExperience workExperience) {
        return WorkExperienceDTO.builder()
                .id(workExperience.getId())
                .employeeId(workExperience.getEmployee().getId())
                .position(workExperience.getPosition())
                .companyName(workExperience.getCompanyName())
                .salary(formatToString(workExperience.getSalary()))
                .yearOfEmployment(String.valueOf(workExperience.getYearOfEmployment()))
                .yearOfResignation(String.valueOf(workExperience.getYearOfResignation()))
                .createdAt(formattedInstantToString(workExperience.getCreatedAt()))
                .createdBy(workExperience.getCreatedBy())
                .updatedAt(workExperience.getUpdatedAt() == null ? null : formattedInstantToString(workExperience.getUpdatedAt()))
                .updatedBy(workExperience.getUpdatedBy() == null ? null : workExperience.getUpdatedBy())
                .build();
    }

    private static List<WorkExperienceDTO> mapToWorkDTOList(List<WorkExperience> workExperienceList) {
        return workExperienceList.stream()
                .map(WorkExperienceServiceImpl::mapToWorkDTO)
                .collect(Collectors.toList());
    }

    private static BigDecimal formatToDecimal(String value) {
        // format 100000 to 100000.00
        return new BigDecimal(value);
    }

    private static String formatToString(BigDecimal value) {
        // format 100000.00 to 100000
        return String.valueOf(value);
    }
}
