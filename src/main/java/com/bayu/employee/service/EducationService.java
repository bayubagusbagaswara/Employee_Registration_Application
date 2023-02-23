package com.bayu.employee.service;

import com.bayu.employee.model.Education;
import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.payload.education.EducationDTO;

import java.util.List;

public interface EducationService {

    EducationDTO createEducation(String userId, CreateEducationRequest createEducationRequest);

    List<EducationDTO> findAllByUserId(String userId);

}
