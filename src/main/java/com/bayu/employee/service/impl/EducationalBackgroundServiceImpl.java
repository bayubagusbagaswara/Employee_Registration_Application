package com.bayu.employee.service.impl;

import com.bayu.employee.model.Education;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.educational.CreateEducationalBackgroundRequest;
import com.bayu.employee.payload.educational.EducationalBackgroundDTO;
import com.bayu.employee.repository.EducationalBackgroundRepository;
import com.bayu.employee.service.EducationalBackgroundService;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationalBackgroundServiceImpl implements EducationalBackgroundService {

    private final EducationalBackgroundRepository educationalBackgroundRepository;
    private final UserService userService;
    private final EmployeeService employeeService;

    public EducationalBackgroundServiceImpl(EducationalBackgroundRepository educationalBackgroundRepository, UserService userService, EmployeeService employeeService) {
        this.educationalBackgroundRepository = educationalBackgroundRepository;
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @Override
    public EducationalBackgroundDTO createEducationalBackground(String userId, CreateEducationalBackgroundRequest createEducationalBackgroundRequest) {
        // cari user by id
        User user = userService.findById(userId);



        return null;
    }

    @Override
    public List<Education> findByUserId(String userId) {
        return null;
    }

    public EducationalBackgroundDTO mapToEducationalBackgroundDTO(Education education) {
        return null;
    }
}
