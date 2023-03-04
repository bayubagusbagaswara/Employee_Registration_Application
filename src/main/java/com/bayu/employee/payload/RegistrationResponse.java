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
public class RegistrationResponse implements Serializable {

    @Serial
    private final static long serialVersionUID = 4283237267686813844L;

    private String id;

    private String username;

    private String email;

    private String password;
}
