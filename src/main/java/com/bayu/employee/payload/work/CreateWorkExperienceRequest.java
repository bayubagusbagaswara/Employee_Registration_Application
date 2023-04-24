package com.bayu.employee.payload.work;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkExperienceRequest implements Serializable {

    private final static long serialVersionUID = 4095520802509285186L;

    private String companyName;

    private String position;

    private String salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime yearOfEmployment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime yearOfResignation;
}
