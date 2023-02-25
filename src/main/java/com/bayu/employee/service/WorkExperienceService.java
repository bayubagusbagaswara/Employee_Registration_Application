package com.bayu.employee.service;

import com.bayu.employee.payload.work.CreateWorkRequest;
import com.bayu.employee.payload.work.WorkDTO;

public interface WorkExperienceService {

    WorkDTO createWork(String userId, CreateWorkRequest createWorkRequest);

}
