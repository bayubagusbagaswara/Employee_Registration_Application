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
public class UpdateWorkRequest implements Serializable {

    @Serial
    private final static long serialVersionUID = 6608153907473027267L;

    private String companyName;

    private String position;

    private String salary;

    private String yearOfEmployment;

    private String yearOfResignation;
}
