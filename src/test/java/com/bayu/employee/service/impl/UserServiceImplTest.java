package com.bayu.employee.service.impl;

import com.bayu.employee.payload.CreateUserRequest;
import com.bayu.employee.payload.UserDTO;
import com.bayu.employee.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    UserService userService;

    @Test
    void checkUsernameIsExists() {
    }

    @Test
    void checkEmailIsExists() {
    }

    @Test
    void register() {
    }

    @Test
    void login() {
    }

    @Test
    void createUser() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .username("bayu")
                .email("bayu@mail.com")
                .password("bayu123")
                .build();

        UserDTO user = userService.createUser(createUserRequest);

        assertNotNull(user.getId());

        log.info("ID: {}", user.getId());
        log.info("Username: {}", user.getUsername());
        log.info("Email: {}", user.getEmail());
        log.info("Password: {}", user.getPassword());
        log.info("Role: {}", user.getRoles());
    }

    @Test
    void getUserById() {
    }

    @Test
    void getUserByUsername() {
    }

    @Test
    void getUserByEmail() {
    }
}