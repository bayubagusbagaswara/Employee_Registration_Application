package com.bayu.employee.service;

import com.bayu.employee.model.Employee;
import com.bayu.employee.payload.employee.CreateEmployeeRequest;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.payload.employee.UpdateEmployeeRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(String id);

    EmployeeDTO getEmployeeByName(String name);

    EmployeeDTO getEmployeeByUserId(String userId);

    Employee findByUserId(String userId);

    EmployeeDTO createEmployee(String userId, CreateEmployeeRequest createEmployeeRequest);

    EmployeeDTO updateEmployee(String id, UpdateEmployeeRequest updateEmployeeRequest);

    void deleteEmployee(String id);

//    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
