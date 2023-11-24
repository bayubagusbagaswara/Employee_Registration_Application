package com.bayu.employee.service.impl;

import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.EducationalBackground;
import com.bayu.employee.model.Employee;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.employee.CreateEmployeeRequest;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.payload.employee.UpdateEmployeeRequest;
import com.bayu.employee.repository.EducationalBackgroundRepository;
import com.bayu.employee.repository.EmployeeRepository;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static com.bayu.employee.util.StringUtil.formattedInstantToString;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final EducationalBackgroundRepository educationalBackgroundRepository;

    // ini gak bisa di inject EducationBackgroundService, menyebabkan circular dependency, karena di EducationalBackgroundService juga butuh EmployeeService

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserService userService, EducationalBackgroundRepository educationalBackgroundRepository) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
        this.educationalBackgroundRepository = educationalBackgroundRepository;
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
        // create hanya dilakukan satu kali saja diawal,
        // jadi kita bisa membuat satu object Education,
        // lalu simpan di EducationRepository beserta employeeId yang sudah ter-create
        User user = userService.findById(userId);

        // create employee
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
                .levelOfEducation(createEmployeeRequest.getLevelOfEducation())
                .build();

        employee.setCreatedAt(Instant.now());
        employee.setCreatedBy("SYSTEM");

        // di object Employee memiliki property tingkatPendidikanTerakhir, jurusan, namaInstansi, tahunLulus

        // save employee
        Employee employeeSaved = employeeRepository.save(employee);

        // create Education dan masukkan employeeId yang sudah tersimpan
        EducationalBackground educationalBackground = EducationalBackground.builder()
                .employee(employeeSaved)
                .levelOfEducation(employeeSaved.getLevelOfEducation())
                .department(createEmployeeRequest.getDepartment())
                .collegeName(createEmployeeRequest.getCollegeName())
                .graduationYear(Integer.valueOf(createEmployeeRequest.getGraduationYear()))
                .build();

        // save Education
        educationalBackgroundRepository.save(educationalBackground);

        return mapToEmployeeDTO(employeeSaved);
    }

    @Override
    public EmployeeDTO updateEmployee(String id, UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));

        validationCheck(updateEmployeeRequest, employee);

        employee.setUpdatedAt(Instant.now());
        employee.setUpdatedBy("SYSTEM");

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
                .salary(String.valueOf(employee.getSalary()))
                .levelOfEducation(employee.getLevelOfEducation())
                .createdAt(formattedInstantToString(employee.getCreatedAt()))
                .createdBy(employee.getCreatedBy())
                .updatedAt(employee.getUpdatedAt() == null ? null : formattedInstantToString(employee.getUpdatedAt()))
                .updatedBy(employee.getUpdatedBy())
                .build();
    }

    private static List<EmployeeDTO> mapToEmployeeDTOList(List<Employee> employeeList) {
        return employeeList.stream()
                .map(EmployeeServiceImpl::mapToEmployeeDTO)
                .toList();
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
        DecimalFormat nf = new DecimalFormat("",
                new DecimalFormatSymbols(new Locale("id", "ID")));
        nf.setParseBigDecimal(true);
//        Locale locale = new Locale("id", "ID");
        return new BigDecimal(currency);
    }
}
