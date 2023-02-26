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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

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
        Employee employee = employeeRepository.findByFullNameContainsIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with name : " + name));
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

        Employee employee = Employee.builder()
                .position(createEmployeeRequest.getPosition())
                .nik(createEmployeeRequest.getNik())
                .firstName(createEmployeeRequest.getFirstName())
                .lastName(createEmployeeRequest.getLastName())
                .fullName(splitAndCapitalize(createEmployeeRequest.getFirstName(), createEmployeeRequest.getLastName()))
                .gender(createEmployeeRequest.getGender())
                .age(Integer.valueOf(createEmployeeRequest.getAge()))
                .placeOfBirth(createEmployeeRequest.getPlaceOfBirth())
                .dateOfBirth(createEmployeeRequest.getDateOfBirth())
                .salary(formatStringToBigDecimal(createEmployeeRequest.getSalary()))
                .user(user)
                .build();

        employeeRepository.save(employee);

        return mapToEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(String id, UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));

        validationCheck(updateEmployeeRequest, employee);

        employeeRepository.save(employee);

        return mapToEmployeeDTO(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));

        employeeRepository.delete(employee);
    }

    private static EmployeeDTO mapToEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .email(employee.getUser().getEmail())
                .position(StringUtils.capitalize(employee.getPosition()))
                .nik(employee.getNik())
                .fullName(employee.getFullName())
                .gender(StringUtils.capitalize(employee.getGender()))
                .age(String.valueOf(employee.getAge()))
                .placeOfBirth(employee.getPlaceOfBirth())
                .dateOfBirth(changeDateFormat(employee.getDateOfBirth()))
                .salary(formatBigDecimalToString(employee.getSalary()))
                .build();
    }

    private static List<EmployeeDTO> mapToEmployeeDTOList(List<Employee> employeeList) {
        return employeeList.stream()
                .map(EmployeeServiceImpl::mapToEmployeeDTO)
                .collect(Collectors.toList());
    }

    private static void validationCheck(UpdateEmployeeRequest updateEmployeeRequest, Employee employee) {
        if (updateEmployeeRequest.getPosition() != null) {
            employee.setPosition(updateEmployeeRequest.getPosition());
        }

        if (updateEmployeeRequest.getNik() != null) {
            employee.setNik(updateEmployeeRequest.getNik());
        }

        if (updateEmployeeRequest.getFirstName() != null) {
            employee.setFirstName(updateEmployeeRequest.getFirstName());
        }

        if (updateEmployeeRequest.getLastName() != null) {
            employee.setLastName(updateEmployeeRequest.getLastName());
        }

        if (updateEmployeeRequest.getGender() != null) {
            employee.setGender(updateEmployeeRequest.getGender());
        }

        if (updateEmployeeRequest.getAge() != null) {
            employee.setAge(Integer.valueOf(updateEmployeeRequest.getAge()));
        }

        if (updateEmployeeRequest.getPlaceOfBirth() != null) {
            employee.setPlaceOfBirth(updateEmployeeRequest.getPlaceOfBirth());
        }

        if (updateEmployeeRequest.getDateOfBirth() != null) {
            employee.setDateOfBirth(updateEmployeeRequest.getDateOfBirth());
        }

        if (updateEmployeeRequest.getFirstName() != null && updateEmployeeRequest.getLastName() != null) {
            employee.setFullName(splitAndCapitalize(updateEmployeeRequest.getFirstName(), updateEmployeeRequest.getLastName()));
        }

        if (updateEmployeeRequest.getSalary() != null) {
            employee.setSalary(formatStringToBigDecimal(updateEmployeeRequest.getSalary()));
        }
    }

    private static String capitalize(String str)
    {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    private static String splitAndCapitalize(String first, String last) {
        List<String> stringList = new ArrayList<>();

        String[] firstSplit = first.split(" ");
        String[] lastSplit = last.split(" ");

        stringList.addAll(Arrays.asList(firstSplit));
        stringList.addAll(Arrays.asList(lastSplit));

        StringBuilder result = new StringBuilder();

        for (String s : stringList) {
            result.append(StringUtils.capitalize(s.toLowerCase()));
            result.append(" ");
        }

        return result.toString();
    }

    private static String changeDateFormat(LocalDate date) {
        return date.getDayOfMonth() +
                " " +
                StringUtils.capitalize(String.valueOf(date.getMonth()).toLowerCase()) +
                " " +
                date.getYear();
    }

    private static String formatBigDecimalToString(BigDecimal currency) {
        DecimalFormat df = new DecimalFormat();
        return df.format(currency);
    }

    private static BigDecimal formatStringToBigDecimal(String currency) {
        NumberFormat nf = new DecimalFormat("",
                new DecimalFormatSymbols(new Locale("id", "ID")));
        ((DecimalFormat) nf).setParseBigDecimal(true);
//        Locale locale = new Locale("id", "ID");
        return new BigDecimal(currency);
    }
}
