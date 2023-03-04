package com.bayu.employee.payload.employee;

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
public class EmployeeDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 757945964007730487L;

    private String id;
    private String username;
    private String email;

    private String position;
    private String nik;

    private String firstName;

    private String lastName;

    private String fullName;

    private String gender;

    private String age;

    private String placeOfBirth;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate dateOfBirth;

    private String dateOfBirth;

    private String salary;

    private String levelOfEducation;
}
