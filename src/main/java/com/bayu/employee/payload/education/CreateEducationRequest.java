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
public class CreateEducationRequest implements Serializable {

    private final static long serialVersionUID = 5633313031356022829L;

    private String levelOfEducation;

    private String department;

    private String collegeName;

    private String graduationYear;
}
