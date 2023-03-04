package com.bayu.employee.payload.work;

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
public class WorkExperienceDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 7172864872644495952L;

    private String id;

    private String userId;

    private String employeeId;

    private String companyName;

    private String position;

    private String salary;

    private String lengthOfWork;

}
