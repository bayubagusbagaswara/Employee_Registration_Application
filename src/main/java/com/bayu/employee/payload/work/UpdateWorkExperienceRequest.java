package com.bayu.employee.payload.work;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkExperienceRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6608153907473027267L;

    private String companyName;

    private String position;

    private String salary;

    private LocalDate yearOfEmployment;

    private LocalDate yearOfResignation;

}
