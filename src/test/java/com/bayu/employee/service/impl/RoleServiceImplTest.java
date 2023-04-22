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

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

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
                .name("admin")
                .build();

        RoleDTO role = roleService.createRole(createRoleRequest);

        assertNotNull(role.getId());

        log.info("ID: {}", role.getId());
        log.info("Name: {}", role.getName());
        log.info("Created At : {}", role.getCreatedAt());
        log.info("Updated At : {}", role.getUpdatedAt());
    }

    @Test
    @Order(2)
    void createRoleUser() {
        CreateRoleRequest createRoleRequest = CreateRoleRequest.builder()
                .name("user")
                .build();

        RoleDTO role = roleService.createRole(createRoleRequest);
        assertNotNull(role.getId());

        log.info("ID: {}", role.getId());
        log.info("Name: {}", role.getName());
    }

    @Test
    void getRoleByName() {
        String name = "user";
        RoleDTO role = roleService.getRoleByName(name);

        log.info("Name : {}", role.getName());
    }

    @Test
    void getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        List<String> roleNames = roles.stream()
                .map(RoleDTO::getName)
                .collect(Collectors.toList());

        for (String roleName : roleNames) {
            log.info("Name : {}", roleName);
        }
        assertEquals(1, roles.size());
    }

    @Test
    void testDeleteRole() {
        String roleId = "1";

        roleService.deleteRole(roleId);
    }

    @Test
    void testZoneId() {
        String s = ZoneId.systemDefault().toString();
        System.out.println(s);
        String s1 = ZoneId.of("Asia/Jakarta").toString();
        System.out.println(s1);
    }
}