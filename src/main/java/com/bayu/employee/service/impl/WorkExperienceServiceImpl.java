package com.bayu.employee.service.impl;

import com.bayu.employee.payload.work.CreateWorkRequest;
import com.bayu.employee.payload.work.WorkDTO;
import com.bayu.employee.repository.WorkExperienceRepository;
import com.bayu.employee.service.WorkExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;

    public WorkExperienceServiceImpl(WorkExperienceRepository workExperienceRepository) {
        this.workExperienceRepository = workExperienceRepository;
    }

    @Override
    public WorkDTO createWork(String userId, CreateWorkRequest createWorkRequest) {
        return null;
    }

    @Override
    public List<WorkDTO> getAllByUserId(String userId) {
        return null;
    }

    @Override
    public WorkDTO getWorkById(String workId) {
        return null;
    }
}
