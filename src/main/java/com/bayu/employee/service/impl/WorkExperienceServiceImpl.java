package com.bayu.employee.service.impl;

import com.bayu.employee.payload.work.CreateWorkRequest;
import com.bayu.employee.payload.work.WorkDTO;
import com.bayu.employee.repository.WorkExperienceRepository;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.WorkExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final EmployeeService employeeService;

    public WorkExperienceServiceImpl(WorkExperienceRepository workExperienceRepository, EmployeeService employeeService) {
        this.workExperienceRepository = workExperienceRepository;
        this.employeeService = employeeService;
    }

    @Override
    public WorkDTO createWorkExperience(String employeeId, CreateWorkRequest createWorkRequest) {
        return null;
    }

    @Override
    public List<WorkDTO> getAllWorkExperiencesByEmployeeId(String employeeId) {
        return null;
    }

    @Override
    public WorkDTO getWorkExperienceById(String workId) {
        return null;
    }
}
