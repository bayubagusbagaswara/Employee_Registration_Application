package com.bayu.employee.payload.education;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEducationRequest {

    @NotBlank(message = "Level of Education must not be blank")
    private String levelOfEducation;

    @NotBlank(message = "Department must not be blank")
    private String department;

    @NotBlank(message = "College Name must not be blank")
    private String collegeName;

    @NotBlank(message = "Graduation Year must not be blank")
    private String graduationYear;
}
