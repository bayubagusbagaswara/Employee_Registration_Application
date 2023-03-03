package com.bayu.employee.service;

import com.bayu.employee.payload.work.CreateWorkRequest;
import com.bayu.employee.payload.work.WorkExperienceDTO;

import java.util.List;

public interface WorkExperienceService {

    WorkExperienceDTO createWorkExperience(String employeeId, CreateWorkRequest createWorkRequest);

    List<WorkExperienceDTO> getAllWorkExperiencesByEmployeeId(String employeeId);

    WorkExperienceDTO getWorkExperienceById(String workId);

}
