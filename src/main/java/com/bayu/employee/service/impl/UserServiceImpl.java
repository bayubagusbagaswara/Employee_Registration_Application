package com.bayu.employee.service.impl;

import com.bayu.employee.model.User;
import com.bayu.employee.payload.LoginRequest;
import com.bayu.employee.payload.LoginResponse;
import com.bayu.employee.payload.RegistrationRequest;
import com.bayu.employee.payload.RegistrationResponse;
import com.bayu.employee.repository.UserRepository;
import com.bayu.employee.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        // create User
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(registrationRequest.getPassword())
                .build();

        userRepository.save(user);
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    public RegistrationResponse mapToRegistrationResponse(User user) {
        return RegistrationResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
