package com.bayu.employee.payload;

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
public class LoginResponse implements Serializable {

    @Serial
    private final static long serialVersionUID = -4489165542681663697L;

    private String id;

    private String username;

    private String email;

    private String password;
}
