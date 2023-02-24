package com.bayu.employee.payload.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {

    private String position;

    private String nik;

    private String firstName;

    private String lastName;

    private String gender;

    private String age;

    private String placeOfBirth;

    private LocalDate dateOfBirth;
}
