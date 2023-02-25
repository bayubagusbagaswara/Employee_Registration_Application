package com.bayu.employee.service;

import com.bayu.employee.payload.work.CreateWorkRequest;
import com.bayu.employee.payload.work.WorkDTO;

import java.util.List;

public interface WorkExperienceService {

    WorkDTO createWork(String userId, CreateWorkRequest createWorkRequest);

    List<WorkDTO> getAllByUserId(String userId);

    WorkDTO getWorkById(String workId);

}
