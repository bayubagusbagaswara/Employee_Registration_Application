package com.bayu.employee.util;

import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.payload.education.UpdateEducationRequest;
import com.bayu.employee.payload.employee.UpdateEmployeeRequest;
import com.bayu.employee.payload.training.CreateTrainingRequest;
import com.bayu.employee.payload.training.UpdateTrainingRequest;
import com.bayu.employee.payload.work.CreateWorkExperienceRequest;
import com.bayu.employee.payload.work.UpdateWorkExperienceRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static com.bayu.employee.util.AppConstants.*;

public class ValidationUtil {

    public static void validationChecksForUpdateEmployeeRequests(UpdateEmployeeRequest updateEmployeeRequest, BindingResult bindingResult) {
        if (updateEmployeeRequest.getPosition().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_EMPLOYEE_REQUEST, FIELD_EMPLOYEE_POSITION, MESSAGE_EMPLOYEE_POSITION));
        }

        if (updateEmployeeRequest.getNik().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_EMPLOYEE_REQUEST, FIELD_EMPLOYEE_NIK, MESSAGE_EMPLOYEE_NIK));
        }

        if (updateEmployeeRequest.getFirstName().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_EMPLOYEE_REQUEST, FIELD_EMPLOYEE_FIRSTNAME, MESSAGE_EMPLOYEE_FIRSTNAME));
        }

        if (updateEmployeeRequest.getLastName().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_EMPLOYEE_REQUEST, FIELD_EMPLOYEE_LASTNAME, MESSAGE_EMPLOYEE_LASTNAME));
        }

        if (updateEmployeeRequest.getGender().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_EMPLOYEE_REQUEST, FIELD_EMPLOYEE_GENDER, MESSAGE_EMPLOYEE_GENDER));
        }

        if (updateEmployeeRequest.getAge().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_EMPLOYEE_REQUEST, FIELD_EMPLOYEE_AGE, MESSAGE_EMPLOYEE_AGE));
        }

        if (updateEmployeeRequest.getPlaceOfBirth().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_EMPLOYEE_REQUEST, FIELD_EMPLOYEE_PLACE_OF_BIRTH, MESSAGE_EMPLOYEE_PLACE_OF_BIRTH));
        }

        if (updateEmployeeRequest.getDateOfBirth() == null) {
            bindingResult.addError(new FieldError(UPDATE_EMPLOYEE_REQUEST, FIELD_EMPLOYEE_DATE_OF_BIRTH, MESSAGE_EMPLOYEE_DATE_OF_BIRTH));
        }
    }

    public static String validationChecksForCreateEducationRequest(CreateEducationRequest createEducationRequest, BindingResult bindingResult) {
        if (createEducationRequest.getLevelOfEducation().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_EDUCATION_REQUEST, FIELD_EDUCATION_LEVEL_OF_EDUCATION, MESSAGE_EDUCATION_LEVEL_OF_EDUCATION));
        }

        if (createEducationRequest.getDepartment().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_EDUCATION_REQUEST, FIELD_EDUCATION_DEPARTMENT, MESSAGE_EDUCATION_DEPARTMENT));
        }

        if (createEducationRequest.getCollegeName().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_EDUCATION_REQUEST, FIELD_EDUCATION_COLLEGE_NAME, MESSAGE_EDUCATION_COLLEGE_NAME));
        }

        if (createEducationRequest.getGraduationYear().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_EDUCATION_REQUEST, FIELD_EDUCATION_GRADUATION_YEAR, MESSAGE_EDUCATION_GRADUATION_YEAR));
        }

        if (bindingResult.hasErrors()) {
            return "education/add_education";
        }
        return null;
    }

    public static String validationChecksForUpdateEducationRequests(UpdateEducationRequest updateEducationRequest, BindingResult bindingResult) {
        if (updateEducationRequest.getLevelOfEducation().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_EDUCATION_REQUEST, FIELD_EDUCATION_LEVEL_OF_EDUCATION, MESSAGE_EDUCATION_LEVEL_OF_EDUCATION));
        }

        if (updateEducationRequest.getDepartment().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_EDUCATION_REQUEST, FIELD_EDUCATION_DEPARTMENT, MESSAGE_EDUCATION_DEPARTMENT));
        }

        if (updateEducationRequest.getCollegeName().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_EDUCATION_REQUEST, FIELD_EDUCATION_COLLEGE_NAME, MESSAGE_EDUCATION_COLLEGE_NAME));
        }

        if (updateEducationRequest.getGraduationYear().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_EDUCATION_REQUEST, FIELD_EDUCATION_GRADUATION_YEAR, MESSAGE_EDUCATION_GRADUATION_YEAR));
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/education/show-update-form/{educationId}";
        }
        return null;
    }

    public static String validationChecksForCreateTrainingRequests(CreateTrainingRequest createTrainingRequest, BindingResult bindingResult) {
        if (createTrainingRequest.getTrainingName().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_TRAINING_REQUEST, FIELD_TRAINING_NAME, MESSAGE_TRAINING_NAME));
        }

        if (createTrainingRequest.getCertificate().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_TRAINING_REQUEST, FIELD_TRAINING_CERTIFICATE, MESSAGE_TRAINING_CERTIFICATE));
        }

        if (createTrainingRequest.getYear().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_TRAINING_REQUEST, FIELD_TRAINING_YEAR, MESSAGE_TRAINING_YEAR));
        }

        if (bindingResult.hasErrors()) {
            return "training/add_training";
        }
        return null;
    }

    public static String validationChecksForUpdateTrainingRequests(UpdateTrainingRequest updateTrainingRequest, BindingResult bindingResult) {
        if (updateTrainingRequest.getTrainingName().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_TRAINING_REQUEST, FIELD_TRAINING_NAME, MESSAGE_TRAINING_NAME));
        }

        if (updateTrainingRequest.getCertificate().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_TRAINING_REQUEST, FIELD_TRAINING_CERTIFICATE, MESSAGE_TRAINING_CERTIFICATE));
        }

        if (updateTrainingRequest.getYear().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_TRAINING_REQUEST, FIELD_TRAINING_YEAR, MESSAGE_TRAINING_YEAR));
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/training/show-update-form/{trainingId}";
        }
        return null;
    }

    public static String validationChecksForCreateWorkExperienceRequests(CreateWorkExperienceRequest createWorkExperienceRequest, BindingResult bindingResult) {
        if (createWorkExperienceRequest.getPosition().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_POSITION, MESSAGE_WORK_EXPERIENCE_POSITION));
        }

        if (createWorkExperienceRequest.getCompanyName().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_COMPANY_NAME, MESSAGE_WORK_EXPERIENCE_COMPANY_NAME));
        }

        if (createWorkExperienceRequest.getSalary().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_SALARY, MESSAGE_WORK_EXPERIENCE_SALARY));
        }

        if (createWorkExperienceRequest.getYearOfEmployment() == null) {
            bindingResult.addError(new FieldError(CREATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_YEAR_OF_EMPLOYMENT, MESSAGE_WORK_EXPERIENCE_YEAR_OF_EMPLOYMENT));
        }

        if (createWorkExperienceRequest.getYearOfResignation() == null) {
            bindingResult.addError(new FieldError(CREATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_YEAR_OF_RESIGNATION, MESSAGE_WORK_EXPERIENCE_YEAR_OF_RESIGNATION));
        }

        if (bindingResult.hasErrors()) {
            return "work/add_work";
        }

        return null;
    }

    public static String validationChecksForUpdateWorkExperienceRequests(UpdateWorkExperienceRequest updateWorkExperienceRequest, BindingResult bindingResult) {
        if (updateWorkExperienceRequest.getPosition().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_POSITION, MESSAGE_WORK_EXPERIENCE_POSITION));
        }

        if (updateWorkExperienceRequest.getCompanyName().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_COMPANY_NAME, MESSAGE_WORK_EXPERIENCE_COMPANY_NAME));
        }

        if (updateWorkExperienceRequest.getSalary().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_SALARY, MESSAGE_WORK_EXPERIENCE_SALARY));
        }

        if (updateWorkExperienceRequest.getYearOfEmployment() == null) {
            bindingResult.addError(new FieldError(UPDATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_YEAR_OF_EMPLOYMENT, MESSAGE_WORK_EXPERIENCE_YEAR_OF_EMPLOYMENT));
        }

        if (updateWorkExperienceRequest.getYearOfResignation() == null) {
            bindingResult.addError(new FieldError(UPDATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_YEAR_OF_RESIGNATION, MESSAGE_WORK_EXPERIENCE_YEAR_OF_RESIGNATION));
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/work/show-update-form/{workExperienceId}";
        }

        return null;
    }

}
