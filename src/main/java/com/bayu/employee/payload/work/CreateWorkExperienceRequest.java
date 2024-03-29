package com.bayu.employee.payload.work;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkExperienceRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 4095520802509285186L;

    private String companyName;

    private String position;

    private String salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearOfEmployment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearOfResignation;
}
