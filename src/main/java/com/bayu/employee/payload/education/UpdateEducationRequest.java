package com.bayu.employee.payload.education;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEducationRequest {

    private String levelOfEducation;

    private String department;

    private String collegeName;

    private String graduationYear;
}
