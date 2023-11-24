package com.bayu.employee.service;

import com.bayu.employee.model.UserInformation;
import com.bayu.employee.payload.employee.CreateEmployeeRequest;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.payload.employee.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {

    // ADMIN
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeByName(String name);

    void deleteEmployee(String id);

    // ADMIN, USER
    EmployeeDTO getEmployeeById(String id);


    // USER
    UserInformation findById(String id);

    UserInformation findByUserId(String userId);

    EmployeeDTO createEmployee(String userId, CreateEmployeeRequest createEmployeeRequest);

    EmployeeDTO updateEmployee(String id, UpdateEmployeeRequest updateEmployeeRequest);



//    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
