package com.bayu.employee.service.impl;

import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.EducationBackground;
import com.bayu.employee.model.Employee;
import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.education.UpdateEducationRequest;
import com.bayu.employee.repository.EducationRepository;
import com.bayu.employee.service.EducationService;
import com.bayu.employee.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final EmployeeService employeeService;

    public EducationServiceImpl(EducationRepository educationRepository, EmployeeService employeeService) {
        this.educationRepository = educationRepository;
        this.employeeService = employeeService;
    }

    @Override
    public EducationDTO createEducation(String employeeId, CreateEducationRequest createEducationRequest) {
        Employee employee = employeeService.findById(employeeId);

        EducationBackground educationBackground = new EducationBackground();
        educationBackground.setLevelOfEducation(createEducationRequest.getLevelOfEducation().toLowerCase());
        educationBackground.setDepartment(createEducationRequest.getDepartment().toLowerCase());
        educationBackground.setCollegeName(createEducationRequest.getCollegeName());
        educationBackground.setGraduationYear(Integer.valueOf(createEducationRequest.getGraduationYear()));
        educationBackground.setEmployee(employee);

        educationRepository.save(educationBackground);

        return mapToEducationDTO(educationBackground);
    }

    @Override
    public List<EducationDTO> getAllByEmployeeId(String employeeId) {
        Sort sorting = Sort.by("graduationYear").ascending();
        List<EducationBackground> educationBackgroundList = educationRepository.findAllByEmployeeId(employeeId, sorting);
        return mapToEducationDTOList(educationBackgroundList);
    }

    @Override
    public EducationDTO findById(String educationId) {
        EducationBackground educationBackground = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id : " + educationId));
        return mapToEducationDTO(educationBackground);
    }

    @Override
    public EducationDTO updateEducation(String educationId, UpdateEducationRequest updateEducationRequest) {
        EducationBackground educationBackground = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id : " + educationId));

        if (updateEducationRequest.getLevelOfEducation() != null) {
            educationBackground.setLevelOfEducation(updateEducationRequest.getLevelOfEducation());
        }

        if (updateEducationRequest.getDepartment() != null) {
            educationBackground.setDepartment(updateEducationRequest.getDepartment());
        }

        if (updateEducationRequest.getCollegeName() != null) {
            educationBackground.setCollegeName(updateEducationRequest.getCollegeName());
        }

        if (updateEducationRequest.getGraduationYear() != null) {
            educationBackground.setGraduationYear(Integer.valueOf(updateEducationRequest.getGraduationYear()));
        }

        educationRepository.save(educationBackground);

        return mapToEducationDTO(educationBackground);
    }

    @Override
    public void deleteEducation(String educationId) {
        EducationBackground educationBackground = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id : " + educationId));

        educationRepository.delete(educationBackground);
    }

    private static EducationDTO mapToEducationDTO(EducationBackground educationBackground) {
        return EducationDTO.builder()
                .id(educationBackground.getId())
                .employeeId(educationBackground.getEmployee().getId())
                .levelOfEducation(StringUtils.upperCase(educationBackground.getLevelOfEducation()))
                .department(capitalizeEachWord(educationBackground.getDepartment()))
                .collegeName(educationBackground.getCollegeName())
                .graduationYear(String.valueOf(educationBackground.getGraduationYear()))
                .build();
    }

    private static List<EducationDTO> mapToEducationDTOList(List<EducationBackground> educationBackgroundList) {
        return educationBackgroundList.stream()
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
