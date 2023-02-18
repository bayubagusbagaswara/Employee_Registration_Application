package com.bayu.employee.service.impl;

import com.bayu.employee.payload.CreateRoleRequest;
import com.bayu.employee.payload.RoleDTO;
import com.bayu.employee.service.RoleService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoleServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImplTest.class);

    @Autowired
    RoleService roleService;

    @Test
    @Order(1)
    void createRoleAdmin() {
        CreateRoleRequest createRoleRequest = CreateRoleRequest.builder()
                .id("admin")
                .name("admin")
                .build();

        RoleDTO role = roleService.createRole(createRoleRequest);

        assertNotNull(role.getId());

        log.info("ID: {}", role.getId());
        log.info("Name: {}", role.getName());
    }

    @Test
    @Order(2)
    void createRoleUser() {
        CreateRoleRequest createRoleRequest = CreateRoleRequest.builder()
                .id("user")
                .name("user")
                .build();

        RoleDTO role = roleService.createRole(createRoleRequest);
        assertNotNull(role.getId());

        log.info("ID: {}", role.getId());
        log.info("Name: {}", role.getName());
    }
}