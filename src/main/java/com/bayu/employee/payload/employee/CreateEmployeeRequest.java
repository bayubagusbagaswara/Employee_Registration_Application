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
public class CreateEmployeeRequest implements Serializable {

    private final static long serialVersionUID = 6045053553655746171L;

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

    private String levelOfEducation;

    private String department;

    private String collegeName;

    private String graduationYear;
}
