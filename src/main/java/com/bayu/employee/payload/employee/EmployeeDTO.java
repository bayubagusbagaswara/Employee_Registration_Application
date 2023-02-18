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

    // jika kita OneToOne dengan table User, maka kita disini bisa mendapatkan data email

    private String position;

    private String name;

    private String age;

    private String address;
}
