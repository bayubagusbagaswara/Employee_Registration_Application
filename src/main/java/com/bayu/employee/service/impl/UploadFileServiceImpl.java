package com.bayu.employee.service.impl;

import com.bayu.employee.model.Employee;
import com.bayu.employee.repository.EmployeeRepository;
import com.bayu.employee.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadFileServiceImpl implements UploadFileService {

    private final EmployeeRepository employeeRepository;

    @Override
    public String processExcelData(MultipartFile file) throws IOException {
        List<Employee> entities = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            // Assuming the data is in the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Employee employee = new Employee();
                // Map Excel data to entity fields
                employee.setFirstName(row.getCell(0).getStringCellValue());
                employee.setLastName(row.getCell(1).getStringCellValue());
                // ...
                entities.add(employee);
            }
            saveEmployeeList(entities);

            return "Success saving to database";

        } catch (Exception e) {
            throw e;
        }
    }

    private void saveEmployeeList(List<Employee> employeeList) {
        // process save to all data employee
        employeeRepository.saveAll(employeeList);
    }

}
