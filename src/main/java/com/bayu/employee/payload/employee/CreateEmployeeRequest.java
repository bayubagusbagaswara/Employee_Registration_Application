package com.bayu.employee.payload.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {

    @NotBlank(message = "Position must not be blank")
    private String position;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Gender must not be blank")
    private String gender;

    @NotBlank(message = "Age must not be blank")
    private String age;

    @NotBlank(message = "Address must not be blank")
    private String address;
}
