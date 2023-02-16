package com.bayu.employee.service.impl;

import com.bayu.employee.exception.AppException;
import com.bayu.employee.model.Role;
import com.bayu.employee.model.User;
import com.bayu.employee.model.enumerator.RoleName;
import com.bayu.employee.payload.*;
import com.bayu.employee.repository.RoleRepository;
import com.bayu.employee.repository.UserRepository;
import com.bayu.employee.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_ROLE_NOT_SET = "User role not set";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean checkUsernameIsExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean checkEmailIsExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void register(RegistrationRequest registrationRequest) {
        // create User
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .enabled(true)
                .build();

        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public UserDTO createUser(CreateUserRequest createUserRequest) {

        // check role

        User user = User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .enabled(true)
                .build();

        Set<Role> roleSet = new HashSet<>();
        if (userRepository.count() == 0) {
            roleSet.add(roleRepository.getByName(RoleName.ADMIN.name())
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
            roleSet.add(roleRepository.getByName(RoleName.USER.name())
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        }
        roleSet.add(roleRepository.getByName(RoleName.USER.name())
                .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        user.setRoles(roleSet);

        userRepository.save(user);

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .enabled(String.valueOf(user.isEnabled()))
                .roles(user.getRoles())
                .build();
    }

    @Override
    public UserDTO getUserById(String id) {
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
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
