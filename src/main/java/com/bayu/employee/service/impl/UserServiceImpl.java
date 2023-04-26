package com.bayu.employee.service.impl;

import com.bayu.employee.exception.AppException;
import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.Role;
import com.bayu.employee.model.User;
import com.bayu.employee.model.enumerator.RoleName;
import com.bayu.employee.payload.*;
import com.bayu.employee.repository.RoleRepository;
import com.bayu.employee.repository.UserRepository;
import com.bayu.employee.security.CustomUserDetails;
import com.bayu.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String USER_ROLE_NOT_SET = "User role not set";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
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
        User user = User.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .enabled(true)
                .build();

        Set<Role> roleSet = new HashSet<>();

        roleSet.add(roleRepository.getRoleByName(RoleName.USER.name())
                .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));

        user.setRoles(roleSet);

        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        log.info("Current User Login : {}", customUserDetails.getEmail());
        return AuthenticationResponse.builder()
                .username(customUserDetails.getUsername())
                .build();
    }

    @Override
    public UserDTO createAdmin(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .enabled(true)
                .build();

        Set<Role> roleSet = new HashSet<>();

        roleSet.add(roleRepository.getRoleByName(RoleName.ADMIN.name())
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
    public UserDTO createAdminAndUser(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .enabled(true)
                .build();

        Set<Role> roleSet = new HashSet<>();

        roleSet.add(roleRepository.getRoleByName(RoleName.ADMIN.name())
                .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        roleSet.add(roleRepository.getRoleByName(RoleName.USER.name())
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
    public UserDTO createUser(CreateUserRequest createUserRequest) {

        // check role

        User user = User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .enabled(true)
                .build();

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.getRoleByName(RoleName.USER.name())
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
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username : " + username));
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
    }

    private static RegistrationResponse mapToRegistrationResponse(User user) {
        return RegistrationResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
