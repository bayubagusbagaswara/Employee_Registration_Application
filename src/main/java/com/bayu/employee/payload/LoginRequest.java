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
public class LoginRequest implements Serializable {

    @Serial
    private final static long serialVersionUID = 3532682402554136843L;

    private String username;

    private String password;
}
