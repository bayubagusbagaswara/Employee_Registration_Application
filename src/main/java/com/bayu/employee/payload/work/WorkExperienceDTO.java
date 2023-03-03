package com.bayu.employee.payload.work;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceDTO {

    private String id;

    private String userId;

    private String employeeId;

    private String companyName;

    private String position;

    private String salary;

    private String lengthOfWork;

}
