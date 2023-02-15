package com.bayu.employee.service;

import com.bayu.employee.payload.LoginRequest;
import com.bayu.employee.payload.LoginResponse;
import com.bayu.employee.payload.RegistrationRequest;
import com.bayu.employee.payload.RegistrationResponse;

public interface UserService {

    RegistrationResponse register(RegistrationRequest registrationRequest);

    LoginResponse login(LoginRequest loginRequest);
}
