package com.bayu.employee.service;

import com.bayu.employee.payload.UserDTO;
import com.bayu.employee.payload.admin.EmployeeAdminDTO;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.work.WorkDTO;

import java.util.List;

public interface AdminService {

    // get all employees
    List<EmployeeAdminDTO> getAllEmployees();

    // get employee by id
    EmployeeDTO getEmployeeById(String employeeId);

    // get user by id
    UserDTO getUserById(String userId);

     List<EducationDTO> getAllEducationsByUserId(String userId);

     List<TrainingDTO> getAllTrainingsByUserId(String userId);

     List<WorkDTO> getAllWorksByUserId(String userId);

     // kita tambahkan pencarian employee berdasarkan nama

    // kita tambahkan pengurutan data berdasarkan property tertentu
}
