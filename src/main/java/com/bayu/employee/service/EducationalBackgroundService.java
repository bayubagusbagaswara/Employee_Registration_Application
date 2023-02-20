package com.bayu.employee.service;

import com.bayu.employee.model.Education;
import com.bayu.employee.payload.educational.CreateEducationalBackgroundRequest;
import com.bayu.employee.payload.educational.EducationalBackgroundDTO;

import java.util.List;

public interface EducationalBackgroundService {

    EducationalBackgroundDTO createEducationalBackground(String userId, CreateEducationalBackgroundRequest createEducationalBackgroundRequest);

    List<Education> findByUserId(String userId);

}
