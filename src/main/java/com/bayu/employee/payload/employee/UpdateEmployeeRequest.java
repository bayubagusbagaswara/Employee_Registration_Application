package com.bayu.employee.payload.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest implements Serializable {

    private final static long serialVersionUID = -6862284935683901056L;

    private String position;

    private String nik;

    private String firstName;

    private String lastName;

    private String gender;

    private String age;

    private String placeOfBirth;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String salary;
}
