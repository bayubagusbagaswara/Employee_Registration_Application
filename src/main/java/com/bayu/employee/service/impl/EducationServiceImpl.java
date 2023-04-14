package com.bayu.employee.service.impl;

import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.EducationalBackground;
import com.bayu.employee.model.Employee;
import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.education.UpdateEducationRequest;
import com.bayu.employee.repository.EducationalBackgroundRepository;
import com.bayu.employee.service.EducationService;
import com.bayu.employee.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationalBackgroundRepository educationalBackgroundRepository;
    private final EmployeeService employeeService;

    public EducationServiceImpl(EducationalBackgroundRepository educationalBackgroundRepository, EmployeeService employeeService) {
        this.educationalBackgroundRepository = educationalBackgroundRepository;
        this.employeeService = employeeService;
    }

    @Override
    public EducationDTO createEducation(String employeeId, CreateEducationRequest createEducationRequest) {
        Employee employee = employeeService.findById(employeeId);

        EducationalBackground educationalBackground = new EducationalBackground();
        educationalBackground.setLevelOfEducation(createEducationRequest.getLevelOfEducation().toLowerCase());
        educationalBackground.setDepartment(createEducationRequest.getDepartment().toLowerCase());
        educationalBackground.setCollegeName(createEducationRequest.getCollegeName());
        educationalBackground.setGraduationYear(Integer.valueOf(createEducationRequest.getGraduationYear()));
        educationalBackground.setEmployee(employee);

        educationalBackgroundRepository.save(educationalBackground);

        return mapToEducationDTO(educationalBackground);
    }

    @Override
    public List<EducationDTO> getAllByEmployeeId(String employeeId) {
        Sort sorting = Sort.by("graduationYear").ascending();
        List<EducationalBackground> educationalBackgroundList = educationalBackgroundRepository.findAllByEmployeeId(employeeId, sorting);
        return mapToEducationDTOList(educationalBackgroundList);
    }

    @Override
    public EducationDTO findById(String educationId) {
        EducationalBackground educationalBackground = educationalBackgroundRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id : " + educationId));
        return mapToEducationDTO(educationalBackground);
    }

    @Override
    public EducationDTO updateEducation(String educationId, UpdateEducationRequest updateEducationRequest) {
        EducationalBackground educationalBackground = educationalBackgroundRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id : " + educationId));

        if (updateEducationRequest.getLevelOfEducation() != null) {
            educationalBackground.setLevelOfEducation(updateEducationRequest.getLevelOfEducation());
        }

        if (updateEducationRequest.getDepartment() != null) {
            educationalBackground.setDepartment(updateEducationRequest.getDepartment());
        }

        if (updateEducationRequest.getCollegeName() != null) {
            educationalBackground.setCollegeName(updateEducationRequest.getCollegeName());
        }

        if (updateEducationRequest.getGraduationYear() != null) {
            educationalBackground.setGraduationYear(Integer.valueOf(updateEducationRequest.getGraduationYear()));
        }

        educationalBackgroundRepository.save(educationalBackground);

        return mapToEducationDTO(educationalBackground);
    }

    @Override
    public void deleteEducation(String educationId) {
        EducationalBackground educationalBackground = educationalBackgroundRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id : " + educationId));

        educationalBackgroundRepository.delete(educationalBackground);
    }

    private static EducationDTO mapToEducationDTO(EducationalBackground educationalBackground) {
        return EducationDTO.builder()
                .id(educationalBackground.getId())
                .employeeId(educationalBackground.getEmployee().getId())
                .levelOfEducation(StringUtils.upperCase(educationalBackground.getLevelOfEducation()))
                .department(capitalizeEachWord(educationalBackground.getDepartment()))
                .collegeName(educationalBackground.getCollegeName())
                .graduationYear(String.valueOf(educationalBackground.getGraduationYear()))
                .build();
    }

    private static List<EducationDTO> mapToEducationDTOList(List<EducationalBackground> educationalBackgroundList) {
        return educationalBackgroundList.stream()
                .map(EducationServiceImpl::mapToEducationDTO)
                .collect(Collectors.toList());
    }

    private static String capitalizeEachWord(String str) {
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
