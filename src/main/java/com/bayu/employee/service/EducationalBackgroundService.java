package com.bayu.employee.service;

import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.education.UpdateEducationRequest;

import java.util.List;

public interface EducationalBackgroundService {

    EducationDTO createEducation(String employeeId, CreateEducationRequest createEducationRequest);

    List<EducationDTO> getAllByEmployeeId(String employeeId);

    EducationDTO getEducationById(String educationId);

    EducationDTO updateEducation(String educationId, UpdateEducationRequest updateEducationRequest);

    void deleteEducation(String educationId);
}
