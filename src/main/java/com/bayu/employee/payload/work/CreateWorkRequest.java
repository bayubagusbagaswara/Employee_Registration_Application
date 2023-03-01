package com.bayu.employee.payload.work;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkRequest {

    private String companyName;

    private String position;

    private String salary;

    private String lengthOfWork;

//    private String yearOfEmployment;

//    private String yearOfResignation;
}
