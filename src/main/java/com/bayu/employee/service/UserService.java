package com.bayu.employee.service;

import com.bayu.employee.payload.*;

public interface UserService {

    Boolean checkUsernameIsExists(String username);

    Boolean checkEmailIsExists(String email);

    void register(RegistrationRequest registrationRequest);

    LoginResponse login(LoginRequest loginRequest);

    UserDTO getUserByEmail(String email);

}
