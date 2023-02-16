package com.bayu.employee.service;

import com.bayu.employee.payload.*;

public interface UserService {

    Boolean checkUsernameIsExists(String username);

    Boolean checkEmailIsExists(String email);

    void register(RegistrationRequest registrationRequest);

    LoginResponse login(LoginRequest loginRequest);

    UserDTO createUser(CreateUserRequest createUserRequest);

    UserDTO getUserById(String id);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByEmail(String email);

}
