package com.bayu.employee.service.impl;

import com.bayu.employee.model.Education;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.repository.EducationRepository;
import com.bayu.employee.service.EducationService;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final UserService userService;
    private final EmployeeService employeeService;

    public EducationServiceImpl(EducationRepository educationRepository, UserService userService, EmployeeService employeeService) {
        this.educationRepository = educationRepository;
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @Override
    public EducationDTO createEducationalBackground(String userId, CreateEducationRequest createEducationRequest) {
        // cari user by id
        User user = userService.findById(userId);



        return null;
    }

    @Override
    public List<Education> findByUserId(String userId) {
        return null;
    }

    public EducationDTO mapToEducationalBackgroundDTO(Education education) {
        return null;
    }
}
