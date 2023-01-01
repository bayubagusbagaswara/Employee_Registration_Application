package com.bayu.employee.service;

import com.bayu.employee.model.Employee;
import com.bayu.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employee = null;

        if (optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
        } else {
            throw new RuntimeException("Employee not found for id : " + id);
        }
        return employee;
    }
}
