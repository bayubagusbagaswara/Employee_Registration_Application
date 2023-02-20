package com.bayu.employee.service.impl;

import com.bayu.employee.model.Education;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.repository.EducationRepository;
import com.bayu.employee.service.EducationService;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public EducationDTO createEducation(String userId, CreateEducationRequest createEducationRequest) {
        User user = userService.findById(userId);

        Education education = new Education();
        education.setLevelOfEducation(createEducationRequest.getLevelOfEducation().toLowerCase());
        education.setDepartment(createEducationRequest.getDepartment().toLowerCase());
        education.setCollegeName(createEducationRequest.getCollegeName().toLowerCase());
        education.setGraduationYear(Integer.valueOf(createEducationRequest.getGraduationYear()));
        education.setUser(user);

        educationRepository.save(education);

        return mapToEducationDTO(education);
    }

    @Override
    public List<Education> findByUserId(String userId) {
        return educationRepository.findAllByUserId(userId);
    }

    public EducationDTO mapToEducationDTO(Education education) {
        return EducationDTO.builder()
                .id(education.getId())
                .userId(education.getUser().getId())
                .levelOfEducation(StringUtils.upperCase(education.getLevelOfEducation()))
                .department(StringUtils.capitalize(education.getDepartment()))
                .collegeName(StringUtils.capitalize(education.getCollegeName()))
                .graduationYear(String.valueOf(education.getGraduationYear()))
                .build();
    }

    public List<EducationDTO> mapToEducationDTOList(List<Education> educationList) {
        return educationList.stream()
                .map(this::mapToEducationDTO)
                .collect(Collectors.toList());
    }

}
