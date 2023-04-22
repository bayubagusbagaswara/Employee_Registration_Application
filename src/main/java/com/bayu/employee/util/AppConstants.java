package com.bayu.employee.util;

import org.springframework.validation.FieldError;

public class AppConstants {

    // EDUCATIONAL BACKGROUND
    public final static String CREATE_EDUCATION_REQUEST = "createEducationRequest";
    public final static String UPDATE_EDUCATION_REQUEST = "updateEducationRequest";
    public final static String FIELD_EDUCATION_LEVEL_OF_EDUCATION = "levelOfEducation";
    public final static String FIELD_EDUCATION_DEPARTMENT = "department";
    public final static String FIELD_EDUCATION_COLLEGE_NAME = "collegeName";
    public final static String FIELD_EDUCATION_GRADUATION_YEAR = "graduationYear";
    public final static String MESSAGE_VALIDATION_FIELD_EDUCATION_LEVEL_OF_EDUCATION = "Tingkat Pendidikan wajib diisi.";
    public final static String MESSAGE_VALIDATION_FIELD_EDUCATION_DEPARTMENT = "Jurusan wajib diisi.";
    public final static String MESSAGE_VALIDATION_FIELD_EDUCATION_COLLEGE_NAME = "Nama Instansi/Perguruan Tinggi wajib diisi.";
    public final static String MESSAGE_VALIDATION_FIELD_EDUCATION_GRADUATION_YEAR = "Tahun Lulus wajib diisi.";

    // WORK EXPERIENCE
    public final static String CREATE_WORK_EXPERIENCE_REQUEST = "createWorkExperienceRequest";
    public final static String UPDATE_WORK_EXPERIENCE_REQUEST = "updateWorkExperienceRequest";
    public final static String FIELD_WORK_EXPERIENCE_POSITION = "position";
    public final static String FIELD_WORK_EXPERIENCE_COMPANY_NAME = "companyName";
    public final static String FIELD_WORK_EXPERIENCE_SALARY = "salary";
    public final static String FIELD_WORK_EXPERIENCE_YEAR_OF_EMPLOYMENT = "yearOfEmployment";
    public final static String FIELD_WORK_EXPERIENCE_YEAR_OF_RESIGNATION = "yearOfResignation";

    public final static String MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_POSITION = "Posisi wajib diisi.";
    public final static String MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_COMPANY_NAME = "Nama Perusahaan wajib diisi.";
    public final static String MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_SALARY = "Gaji wajib diisi.";
    public final static String MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_YEAR_OF_EMPLOYMENT = "Tahun Masuk wajib diisi.";
    public final static String MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_YEAR_OF_RESIGNATION = "Tahun Keluar wajid diisi.";
}
