package com.bayu.employee.payload.work;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkRequest implements Serializable {

    @Serial
    private final static long serialVersionUID = 4095520802509285186L;

    private String companyName;

    private String position;

    private String salary;

    private String lengthOfWork;

//    private String yearOfEmployment;

//    private String yearOfResignation;
}
