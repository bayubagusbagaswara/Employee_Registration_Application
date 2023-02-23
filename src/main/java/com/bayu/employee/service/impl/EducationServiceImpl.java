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
import org.springframework.data.domain.Sort;
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
        education.setCollegeName(createEducationRequest.getCollegeName());
        education.setGraduationYear(Integer.valueOf(createEducationRequest.getGraduationYear()));
        education.setUser(user);

        educationRepository.save(education);

        return mapToEducationDTO(education);
    }

    @Override
    public List<EducationDTO> findAllByUserId(String userId) {
        // sorting by tahun secara ascending
        Sort sorting = Sort.by("graduationYear").ascending();
        List<Education> educationList = educationRepository.findAllByUserId(userId, sorting);
        return mapToEducationDTOList(educationList);
    }

    public EducationDTO mapToEducationDTO(Education education) {
        return EducationDTO.builder()
                .id(education.getId())
                .userId(education.getUser().getId())
                .levelOfEducation(StringUtils.upperCase(education.getLevelOfEducation()))
                .department(capitalizeEachWord(education.getDepartment()))
                .collegeName(education.getCollegeName())
                .graduationYear(String.valueOf(education.getGraduationYear()))
                .build();
    }

    public List<EducationDTO> mapToEducationDTOList(List<Education> educationList) {
        return educationList.stream()
                .map(this::mapToEducationDTO)
                .collect(Collectors.toList());
    }

    public String capitalizeEachWord(String str) {
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || str.charAt(i - 1) == ' ') {
                word.append(Character.toUpperCase(str.charAt(i)));
            } else {
                word.append(str.charAt(i));
            }
        }
        return word.toString();
    }


}
