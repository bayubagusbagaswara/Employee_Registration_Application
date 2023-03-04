package com.bayu.employee.payload.education;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEducationRequest implements Serializable {

    @Serial
    private final static long serialVersionUID = 7334582015887164157L;

    private String levelOfEducation;

    private String department;

    private String collegeName;

    private String graduationYear;
}
