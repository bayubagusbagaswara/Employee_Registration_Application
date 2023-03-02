package com.bayu.employee.service;

import com.bayu.employee.payload.work.CreateWorkRequest;
import com.bayu.employee.payload.work.WorkDTO;

import java.util.List;

public interface WorkExperienceService {

    WorkDTO createWorkExperience(String employeeId, CreateWorkRequest createWorkRequest);

    List<WorkDTO> getAllWorkExperiencesByEmployeeId(String employeeId);

    WorkDTO getWorkExperienceById(String workId);

}
