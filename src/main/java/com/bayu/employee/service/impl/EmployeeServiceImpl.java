package com.bayu.employee.service.impl;

import com.bayu.employee.exception.ResourceNotFoundException;
import com.bayu.employee.model.EducationalBackground;
import com.bayu.employee.model.UserInformation;
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
        UserInformation userInformation = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));
        return mapToEmployeeDTO(userInformation);
    }

    @Override
    public EmployeeDTO getEmployeeByName(String name) {
        UserInformation userInformation = employeeRepository.findByFullNameContainsIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with name : " + name));
        return mapToEmployeeDTO(userInformation);
    }

    @Override
    public UserInformation findById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));
    }

    @Override
    public UserInformation findByUserId(String userId) {
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
        UserInformation userInformation = UserInformation.builder()
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

        userInformation.setCreatedAt(Instant.now());
        userInformation.setCreatedBy("SYSTEM");

        // di object Employee memiliki property tingkatPendidikanTerakhir, jurusan, namaInstansi, tahunLulus

        // save employee
        UserInformation userInformationSaved = employeeRepository.save(userInformation);

        // create Education dan masukkan employeeId yang sudah tersimpan
        EducationalBackground educationalBackground = EducationalBackground.builder()
                .userInformation(userInformationSaved)
                .levelOfEducation(userInformationSaved.getLevelOfEducation())
                .department(createEmployeeRequest.getDepartment())
                .collegeName(createEmployeeRequest.getCollegeName())
                .graduationYear(Integer.valueOf(createEmployeeRequest.getGraduationYear()))
                .build();

        // save Education
        educationalBackgroundRepository.save(educationalBackground);

        return mapToEmployeeDTO(userInformationSaved);
    }

    @Override
    public EmployeeDTO updateEmployee(String id, UpdateEmployeeRequest updateEmployeeRequest) {
        UserInformation userInformation = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));

        validationCheck(updateEmployeeRequest, userInformation);

        userInformation.setUpdatedAt(Instant.now());
        userInformation.setUpdatedBy("SYSTEM");

        employeeRepository.save(userInformation);

        return mapToEmployeeDTO(userInformation);
    }

    @Override
    public void deleteEmployee(String id) {
        UserInformation userInformation = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));

        employeeRepository.delete(userInformation);
    }

    private static EmployeeDTO mapToEmployeeDTO(UserInformation userInformation) {
        return EmployeeDTO.builder()
                .id(userInformation.getId())
                .email(userInformation.getUser().getEmail())
                .position(StringUtils.capitalize(userInformation.getPosition()))
                .nik(userInformation.getNik())
                .fullName(userInformation.getFullName())
                .gender(StringUtils.capitalize(userInformation.getGender()))
                .age(String.valueOf(userInformation.getAge()))
                .placeOfBirth(userInformation.getPlaceOfBirth())
                .dateOfBirth(changeDateFormat(userInformation.getDateOfBirth()))
                .salary(String.valueOf(userInformation.getSalary()))
                .levelOfEducation(userInformation.getLevelOfEducation())
                .createdAt(formattedInstantToString(userInformation.getCreatedAt()))
                .createdBy(userInformation.getCreatedBy())
                .updatedAt(userInformation.getUpdatedAt() == null ? null : formattedInstantToString(userInformation.getUpdatedAt()))
                .updatedBy(userInformation.getUpdatedBy())
                .build();
    }

    private static List<EmployeeDTO> mapToEmployeeDTOList(List<UserInformation> userInformationList) {
        return userInformationList.stream()
                .map(EmployeeServiceImpl::mapToEmployeeDTO)
                .toList();
    }

    private static void validationCheck(UpdateEmployeeRequest updateEmployeeRequest, UserInformation userInformation) {
        if (updateEmployeeRequest.getPosition() != null) {
            userInformation.setPosition(updateEmployeeRequest.getPosition());
        }

        if (updateEmployeeRequest.getNik() != null) {
            userInformation.setNik(updateEmployeeRequest.getNik());
        }

        if (updateEmployeeRequest.getFirstName() != null) {
            userInformation.setFirstName(updateEmployeeRequest.getFirstName());
        }

        if (updateEmployeeRequest.getLastName() != null) {
            userInformation.setLastName(updateEmployeeRequest.getLastName());
        }

        if (updateEmployeeRequest.getGender() != null) {
            userInformation.setGender(updateEmployeeRequest.getGender());
        }

        if (updateEmployeeRequest.getAge() != null) {
            userInformation.setAge(Integer.valueOf(updateEmployeeRequest.getAge()));
        }

        if (updateEmployeeRequest.getPlaceOfBirth() != null) {
            userInformation.setPlaceOfBirth(updateEmployeeRequest.getPlaceOfBirth());
        }

        if (updateEmployeeRequest.getDateOfBirth() != null) {
            userInformation.setDateOfBirth(updateEmployeeRequest.getDateOfBirth());
        }

        if (updateEmployeeRequest.getFirstName() != null && updateEmployeeRequest.getLastName() != null) {
            userInformation.setFullName(splitAndCapitalize(updateEmployeeRequest.getFirstName(), updateEmployeeRequest.getLastName()));
        }

        if (updateEmployeeRequest.getSalary() != null) {
            userInformation.setSalary(formatStringToBigDecimal(updateEmployeeRequest.getSalary()));
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
