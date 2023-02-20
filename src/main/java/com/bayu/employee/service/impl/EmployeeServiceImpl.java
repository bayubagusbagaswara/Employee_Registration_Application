package com.bayu.employee.service.impl;

import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.Employee;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.employee.CreateEmployeeRequest;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.payload.employee.UpdateEmployeeRequest;
import com.bayu.employee.repository.EmployeeRepository;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserService userService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserService userService) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return mapToEmployeeDTOList(employeeRepository.findAll());
    }

    @Override
    public EmployeeDTO getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));
        return mapToEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO getEmployeeByName(String name) {
        Employee employee = employeeRepository.findByNameContainsIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with name : " + name));
        return mapToEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO getEmployeeByUserId(String userId) {
        Employee employee = employeeRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with user id : " + userId));
        return mapToEmployeeDTO(employee);
    }

    @Override
    public Employee findById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));
    }

    @Override
    public Employee findByUserId(String userId) {
        return employeeRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with user id : " + userId));
    }

    @Override
    public EmployeeDTO createEmployee(String userId, CreateEmployeeRequest createEmployeeRequest) {
        User user = userService.findById(userId);

        Employee employee = new Employee();
        employee.setPosition(createEmployeeRequest.getPosition().toLowerCase());
        employee.setName(createEmployeeRequest.getName().toLowerCase());
        employee.setAge(Integer.valueOf(createEmployeeRequest.getAge()));
        employee.setGender(createEmployeeRequest.getGender().toLowerCase());
        employee.setAddress(createEmployeeRequest.getAddress());
        employee.setUser(user);

        employeeRepository.save(employee);

        return mapToEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(String id, UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));

        if (updateEmployeeRequest.getName() != null) {
            employee.setName(updateEmployeeRequest.getName());
        }

        if (updateEmployeeRequest.getPosition() != null) {
            employee.setPosition(updateEmployeeRequest.getPosition());
        }

        if (updateEmployeeRequest.getAge() != null) {
            employee.setAge(Integer.valueOf(updateEmployeeRequest.getAge()));
        }

        if (updateEmployeeRequest.getAddress() != null) {
            employee.setAddress(updateEmployeeRequest.getAddress());
        }

        employeeRepository.save(employee);

        return mapToEmployeeDTO(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));

        employeeRepository.delete(employee);
    }

    public EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .position(StringUtils.capitalize(employee.getPosition()))
                .name(StringUtils.capitalize(employee.getName()))
                .email(employee.getUser().getEmail())
                .age(String.valueOf(employee.getAge()))
                .gender(StringUtils.capitalize(employee.getGender()))
                .address(employee.getAddress())
                .build();
    }

    public List<EmployeeDTO> mapToEmployeeDTOList(List<Employee> employeeList) {
        return employeeList.stream()
                .map(this::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    public static String capitalize(String str)
    {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

}
