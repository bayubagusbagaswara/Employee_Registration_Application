package com.bayu.employee.service.impl;

import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.Employee;
import com.bayu.employee.model.WorkExperience;
import com.bayu.employee.payload.work.CreateWorkRequest;
import com.bayu.employee.payload.work.WorkExperienceDTO;
import com.bayu.employee.repository.WorkExperienceRepository;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.WorkExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final EmployeeService employeeService;

    public WorkExperienceServiceImpl(WorkExperienceRepository workExperienceRepository, EmployeeService employeeService) {
        this.workExperienceRepository = workExperienceRepository;
        this.employeeService = employeeService;
    }

    @Override
    public WorkExperienceDTO createWorkExperience(String employeeId, CreateWorkRequest createWorkRequest) {
        // cari employee by id dulu
        Employee employee = employeeService.findById(employeeId);

        // buat object WorkExperience
        WorkExperience workExperience = WorkExperience.builder()
                .position(createWorkRequest.getPosition())
                .companyName(createWorkRequest.getCompanyName())
                // salary convert dulu ke BigDecimal
//                .salary(createWorkRequest.)
                .employee(employee)
                .build();

        // save work experience
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

    private WorkExperienceDTO mapToWorkDTO(WorkExperience workExperience) {
        return WorkExperienceDTO.builder()
                .id(workExperience.getId())
                .employeeId(workExperience.getEmployee().getId())
                .position(workExperience.getPosition())
                .companyName(workExperience.getCompanyName())
                .salary(String.valueOf(workExperience.getSalary()))
                .build();
    }

    private List<WorkExperienceDTO> mapToWorkDTOList(List<WorkExperience> workExperienceList) {
        return workExperienceList.stream()
                .map(this::mapToWorkDTO)
                .collect(Collectors.toList());
    }

    // private convert from String to BigDecimal
}
