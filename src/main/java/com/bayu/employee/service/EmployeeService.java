package com.bayu.employee.service;

import com.bayu.employee.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployeeById(String id);

    void deleteEmployeeById(String id);

    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
