package com.bayu.employee.service;

import com.bayu.employee.model.EducationalBackground;
import com.bayu.employee.payload.educational.CreateEducationalBackgroundRequest;
import com.bayu.employee.payload.educational.EducationBackgroundDTO;

import java.util.List;

public interface EducationalBackgroundService {

    // create Educational Background berdasarkan userId, dimana userId ini akan kita gunakan untuk mendapatkan data Employee
    EducationBackgroundDTO createEducationalBackground(String employeeId, CreateEducationalBackgroundRequest createEducationalBackgroundRequest);

    // get Educational Background by Employee Id
    List<EducationalBackground> findByEmployeeId(String employeeId);

    List<EducationBackgroundDTO> getByEmployeeId(String employeeId);
}
