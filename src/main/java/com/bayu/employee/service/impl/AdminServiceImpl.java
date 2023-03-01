package com.bayu.employee.service.impl;

import com.bayu.employee.payload.admin.EmployeeAdminDTO;
import com.bayu.employee.repository.EmployeeRepository;
import com.bayu.employee.repository.UserRepository;
import com.bayu.employee.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<EmployeeAdminDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employee -> {
                    return EmployeeAdminDTO.builder()
                            .position(employee.getPosition())
                            .nik(employee.getNik())
                            .fullName(employee.getFullName())
                            .build();
                }).collect(Collectors.toList());
    }

}
