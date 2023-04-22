package com.bayu.employee.service;

import com.bayu.employee.payload.work.CreateWorkExperienceRequest;
import com.bayu.employee.payload.work.UpdateWorkExperienceRequest;
import com.bayu.employee.payload.work.WorkExperienceDTO;

import java.util.List;

public interface WorkExperienceService {

    WorkExperienceDTO createWorkExperience(String employeeId, CreateWorkExperienceRequest createWorkExperienceRequest);

    List<WorkExperienceDTO> getAllWorkExperiencesByEmployeeId(String employeeId);

    WorkExperienceDTO getWorkExperienceById(String workId);

    WorkExperienceDTO updateWorkExperience(String workExperienceId, UpdateWorkExperienceRequest updateWorkExperienceRequest);

    void deleteWorkExperience(String workExperienceId);

}
