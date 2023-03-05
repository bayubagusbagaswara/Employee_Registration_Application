package com.bayu.employee.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse implements Serializable {

    private final static long serialVersionUID = 7894749800380801427L;

    private String username;
}
