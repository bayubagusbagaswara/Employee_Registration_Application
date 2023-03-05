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
public class CreateWorkRequest implements Serializable {

    private final static long serialVersionUID = 4095520802509285186L;

    private String companyName;

    private String position;

    private String salary;

    private String yearOfEmployment;

    private String yearOfResignation;
}
