package com.bayu.employee.service.impl;

import com.bayu.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserInformationServiceImplTest {

    private final static Logger log = LoggerFactory.getLogger(UserInformationServiceImplTest.class);

    @Autowired
    EmployeeService employeeService;

    @Test
    void createEmployee() {
    }
}