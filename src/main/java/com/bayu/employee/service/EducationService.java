package com.bayu.employee.service;

import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.education.UpdateEducationRequest;

import java.util.List;

public interface EducationService {

    EducationDTO createEducation(String userId, CreateEducationRequest createEducationRequest);

    List<EducationDTO> findAllByUserId(String userId);

    EducationDTO findById(String educationId);

    EducationDTO updateEducation(String educationId, UpdateEducationRequest updateEducationRequest);

    void deleteEducation(String educationId);
}
