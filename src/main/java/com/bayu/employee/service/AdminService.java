package com.bayu.employee.service;

import com.bayu.employee.payload.UserDTO;
import com.bayu.employee.payload.admin.EmployeeAdminDTO;
import com.bayu.employee.payload.employee.EmployeeDTO;

import java.util.List;

public interface AdminService {

    // get all employees
    List<EmployeeAdminDTO> getAllEmployees();

    // get employee by id
    EmployeeDTO getEmployeeById(String employeeId);

    // get user by id
    UserDTO getUserById(String userId);
}
