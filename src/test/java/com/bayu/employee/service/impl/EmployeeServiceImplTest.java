package com.bayu.employee.service.impl;

import com.bayu.employee.payload.employee.CreateEmployeeRequest;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(EmployeeServiceImplTest.class);

    @Autowired
    EmployeeService employeeService;

    @Test
    void createEmployee() {
        String userId = "f5342629-1d96-4181-9f29-aea06fa15012";
        CreateEmployeeRequest createEmployeeRequest = CreateEmployeeRequest.builder()
                .name("Bayu Bagaswara")
                .position("Backend Developer")
                .age("26")
                .gender("Laki-laki")
                .address("Kediri")
                .build();

        EmployeeDTO employee = employeeService.createEmployee(userId, createEmployeeRequest);

        assertNotNull(employee.getId());

        log.info("Name: {}", employee.getName());
        log.info("Gender: {}", employee.getGender());
    }
}