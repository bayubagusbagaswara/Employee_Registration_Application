package com.bayu.employee.payload.work;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkExperienceRequest implements Serializable {

    private final static long serialVersionUID = 6608153907473027267L;

    private String companyName;

    private String position;

    private String salary;

    private String yearOfEmployment;

    private String yearOfResignation;
}
