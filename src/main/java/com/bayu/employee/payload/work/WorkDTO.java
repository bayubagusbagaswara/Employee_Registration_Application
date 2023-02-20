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
public class WorkDTO {

    private String id;

    private String userId;

    private String companyName;

    private String position;

    private BigDecimal salary;

    private String yearOfEmployment;

    private String yearOfResignation;

}
