package com.bayu.employee.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest implements Serializable {

    private final static long serialVersionUID = -5547164059361684602L;

    @NotBlank(message = "Username must not be blank")
    private String username;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Length(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Repeat Password must not be blank")
    private String repeatPassword;
}
