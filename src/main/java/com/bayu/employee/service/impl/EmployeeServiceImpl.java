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
        return employeeRepository.findAll()
                .stream()
                .map(employee -> EmployeeDTO.builder()
                        .id(employee.getId())
                        .position(employee.getPosition())
                        .name(employee.getName())
                        .age(String.valueOf(employee.getAge()))
                        .address(employee.getAddress())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));
        return EmployeeDTO.builder()
                .id(employee.getId())
                .position(employee.getPosition())
                .name(employee.getName())
                .age(String.valueOf(employee.getAge()))
                .address(employee.getAddress())
                .build();
    }

    @Override
    public EmployeeDTO getEmployeeByName(String name) {
        Employee employee = employeeRepository.findByNameContainsIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with name : " + name));
        return EmployeeDTO.builder()
                .id(employee.getId())
                .position(employee.getPosition())
                .name(employee.getName())
                .age(String.valueOf(employee.getAge()))
                .address(employee.getAddress())
                .build();
    }

    @Override
    public EmployeeDTO getEmployeeByUserId(String userId) {
        Employee employee = employeeRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with user id : " + userId));
        return EmployeeDTO.builder()
                .id(employee.getId())
                .position(employee.getPosition())
                .name(employee.getName())
                .age(String.valueOf(employee.getAge()))
                .address(employee.getAddress())
                .build();
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
        // kita harus cek user by id, panggil userService
        User user = userService.findById(userId);

        Employee employee = Employee.builder()
                .name(createEmployeeRequest.getName())
                .position(createEmployeeRequest.getPosition())
                .age(Integer.valueOf(createEmployeeRequest.getAge()))
                .address(createEmployeeRequest.getAddress())
                .user(user)
                .build();

        employeeRepository.save(employee);

        return EmployeeDTO.builder()
                .id(employee.getId())
                .username(employee.getUser().getUsername())
                .email(employee.getUser().getEmail())
                .position(employee.getPosition())
                .name(employee.getName())
                .age(String.valueOf(employee.getAge()))
                .address(employee.getAddress())
                .build();
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

        return EmployeeDTO.builder()
                .id(employee.getId())
                .position(employee.getPosition())
                .name(employee.getName())
                .age(String.valueOf(employee.getAge()))
                .address(employee.getAddress())
                .build();
    }

    @Override
    public void deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));

        employeeRepository.delete(employee);
    }

}
