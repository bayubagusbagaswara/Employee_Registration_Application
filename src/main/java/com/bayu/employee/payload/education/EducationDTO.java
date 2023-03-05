package com.bayu.employee.payload.education;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO implements Serializable {

    private final static long serialVersionUID = 5429895131311271533L;

    private String id;

    private String userId;

    private String employeeId;

    private String levelOfEducation;

    private String department;

    private String collegeName;

    private String graduationYear;
}
