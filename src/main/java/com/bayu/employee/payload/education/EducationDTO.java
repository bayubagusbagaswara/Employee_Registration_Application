package com.bayu.employee.payload.education;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO {

    private String id;

    private String userId;

    private String employeeId;

    private String levelOfEducation;

    private String department;

    private String collegeName;

    private String graduationYear;
}
