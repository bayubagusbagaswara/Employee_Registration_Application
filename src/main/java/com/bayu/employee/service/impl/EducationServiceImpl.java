package com.bayu.employee.service.impl;

import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.Education;
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

        Education education = new Education();
        education.setLevelOfEducation(createEducationRequest.getLevelOfEducation().toLowerCase());
        education.setDepartment(createEducationRequest.getDepartment().toLowerCase());
        education.setCollegeName(createEducationRequest.getCollegeName());
        education.setGraduationYear(Integer.valueOf(createEducationRequest.getGraduationYear()));
        education.setEmployee(employee);

        educationRepository.save(education);

        return mapToEducationDTO(education);
    }

    @Override
    public List<EducationDTO> getAllByEmployeeId(String employeeId) {
        Sort sorting = Sort.by("graduationYear").ascending();
        List<Education> educationList = educationRepository.findAllByEmployeeId(employeeId, sorting);
        return mapToEducationDTOList(educationList);
    }

    @Override
    public EducationDTO findById(String educationId) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id : " + educationId));
        return mapToEducationDTO(education);
    }

    @Override
    public EducationDTO updateEducation(String educationId, UpdateEducationRequest updateEducationRequest) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id : " + educationId));

        if (updateEducationRequest.getLevelOfEducation() != null) {
            education.setLevelOfEducation(updateEducationRequest.getLevelOfEducation());
        }

        if (updateEducationRequest.getDepartment() != null) {
            education.setDepartment(updateEducationRequest.getDepartment());
        }

        if (updateEducationRequest.getCollegeName() != null) {
            education.setCollegeName(updateEducationRequest.getCollegeName());
        }

        if (updateEducationRequest.getGraduationYear() != null) {
            education.setGraduationYear(Integer.valueOf(updateEducationRequest.getGraduationYear()));
        }

        educationRepository.save(education);

        return mapToEducationDTO(education);
    }

    @Override
    public void deleteEducation(String educationId) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id : " + educationId));

        educationRepository.delete(education);
    }

    private static EducationDTO mapToEducationDTO(Education education) {
        return EducationDTO.builder()
                .id(education.getId())
                .employeeId(education.getEmployee().getId())
                .levelOfEducation(StringUtils.upperCase(education.getLevelOfEducation()))
                .department(capitalizeEachWord(education.getDepartment()))
                .collegeName(education.getCollegeName())
                .graduationYear(String.valueOf(education.getGraduationYear()))
                .build();
    }

    private static List<EducationDTO> mapToEducationDTOList(List<Education> educationList) {
        return educationList.stream()
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
