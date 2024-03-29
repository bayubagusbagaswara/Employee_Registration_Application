package com.bayu.employee.payload.work;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceDTO implements Serializable {

    private static final long serialVersionUID = 7172864872644495952L;

    private String id;

    private String userId;

    private String employeeId;

    private String companyName;

    private String position;

    private String salary;

    private LocalDate yearOfEmployment;

    private LocalDate yearOfResignation;

    private String lengthOfWork;

    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;

}
