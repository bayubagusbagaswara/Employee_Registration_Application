package com.bayu.employee.service.impl;

import com.bayu.employee.model.EducationalBackground;
import com.bayu.employee.model.Employee;
import com.bayu.employee.payload.educational.CreateEducationalBackgroundRequest;
import com.bayu.employee.payload.educational.EducationBackgroundDTO;
import com.bayu.employee.repository.EducationalBackgroundRepository;
import com.bayu.employee.service.EducationalBackgroundService;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public EducationBackgroundDTO createEducationalBackground(String employeeId, CreateEducationalBackgroundRequest createEducationalBackgroundRequest) {
        // cari employee
        Employee employee = employeeService.findById(employeeId);

        // create Educational Background
        EducationalBackground educationalBackground = EducationalBackground.builder()
                .name(createEducationalBackgroundRequest.getName())
                .major(createEducationalBackgroundRequest.getMajor())
                .year(Integer.valueOf(createEducationalBackgroundRequest.getYear()))
                .build();

        educationalBackgroundRepository.save(educationalBackground);

        return EducationBackgroundDTO.builder()
                .id(educationalBackground.getId())
                .name(educationalBackground.getName())
                .major(educationalBackground.getMajor())
                .year(String.valueOf(educationalBackground.getYear()))
                .build();
    }

    @Override
    public List<EducationalBackground> findByEmployeeId(String employeeId) {
//        return educationalBackgroundRepository.findByEmployeeId(employeeId);
        return null;
    }

    @Override
    public List<EducationBackgroundDTO> getByEmployeeId(String employeeId) {
//        List<EducationalBackground> educationalBackgroundList = educationalBackgroundRepository.findByEmployeeId(employeeId);
//        return educationalBackgroundList.stream()
//                .map(educationalBackground -> {
//                    return EducationBackgroundDTO.builder()
//                            .id(educationalBackground.getId())
//                            .employeeId(educationalBackground.getEmployee().getId())
//                            .name(educationalBackground.getName())
//                            .major(educationalBackground.getMajor())
//                            .year(String.valueOf(educationalBackground.getYear()))
//                            .build();
//                }).collect(Collectors.toList());
        return null;
    }

}
