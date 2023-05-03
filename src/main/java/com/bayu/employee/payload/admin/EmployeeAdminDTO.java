package com.bayu.employee.payload.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAdminDTO implements Serializable {

    private final static long serialVersionUID = -1007974275382158669L;

    private String id;

    private String position;
    private String fullName;

    private String age;
    private String gender;

    private String levelOfEducation;

    private String createdAt;

    private String createdBy;

    private String updatedAt;

    private String updatedBy;
}
