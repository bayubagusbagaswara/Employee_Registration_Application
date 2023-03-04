package com.bayu.employee.payload.admin;

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
public class EmployeeAdminDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = -1007974275382158669L;

    private String position;
    private String nik;
    private String fullName;
}
