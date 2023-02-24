package com.bayu.employee.payload.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

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

    private String dateOfBirth;
}
