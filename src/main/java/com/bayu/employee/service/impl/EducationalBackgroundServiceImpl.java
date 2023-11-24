package com.bayu.employee.service.impl;

import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.EducationalBackground;
import com.bayu.employee.model.UserInformation;
import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.education.UpdateEducationRequest;
import com.bayu.employee.repository.EducationalBackgroundRepository;
import com.bayu.employee.service.EducationalBackgroundService;
import com.bayu.employee.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static com.bayu.employee.util.StringUtil.formattedInstantToString;

@Service
public class EducationalBackgroundServiceImpl implements EducationalBackgroundService {

    private final EducationalBackgroundRepository educationalBackgroundRepository;
    private final EmployeeService employeeService;

    public EducationalBackgroundServiceImpl(EducationalBackgroundRepository educationalBackgroundRepository, EmployeeService employeeService) {
        this.educationalBackgroundRepository = educationalBackgroundRepository;
        this.employeeService = employeeService;
    }

    @Override
    public EducationDTO createEducation(String employeeId, CreateEducationRequest createEducationRequest) {
        UserInformation userInformation = employeeService.findById(employeeId);

        EducationalBackground educationalBackground = new EducationalBackground();
        educationalBackground.setLevelOfEducation(createEducationRequest.getLevelOfEducation().toLowerCase());
        educationalBackground.setDepartment(createEducationRequest.getDepartment().toLowerCase());
        educationalBackground.setCollegeName(createEducationRequest.getCollegeName());
        educationalBackground.setGraduationYear(Integer.valueOf(createEducationRequest.getGraduationYear()));
        educationalBackground.setUserInformation(userInformation);

        educationalBackground.setCreatedAt(Instant.now());
        educationalBackground.setCreatedBy("SYSTEM");

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
    public EducationDTO getEducationById(String educationId) {
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

        educationalBackground.setUpdatedAt(Instant.now());
        educationalBackground.setUpdatedBy("SYSTEM");

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
                .employeeId(educationalBackground.getUserInformation().getId())
                .levelOfEducation(StringUtils.upperCase(educationalBackground.getLevelOfEducation()))
                .department(capitalizeEachWord(educationalBackground.getDepartment()))
                .collegeName(educationalBackground.getCollegeName())
                .graduationYear(String.valueOf(educationalBackground.getGraduationYear()))
                .createdAt(formattedInstantToString(educationalBackground.getCreatedAt()))
                .createdBy(educationalBackground.getCreatedBy())
                .updatedAt(educationalBackground.getUpdatedAt() == null ? null : formattedInstantToString(educationalBackground.getUpdatedAt()))
                .updatedBy(educationalBackground.getUpdatedBy() == null ? null : educationalBackground.getUpdatedBy())
                .build();
    }

    private static List<EducationDTO> mapToEducationDTOList(List<EducationalBackground> educationalBackgroundList) {
        return educationalBackgroundList.stream()
                .map(EducationalBackgroundServiceImpl::mapToEducationDTO)
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
