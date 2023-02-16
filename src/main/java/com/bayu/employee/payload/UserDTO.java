package com.bayu.employee.payload;

import com.bayu.employee.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;

    private String username;

    private String email;

    private String enabled;

    private String password;

    private Set<Role> roles;
}
