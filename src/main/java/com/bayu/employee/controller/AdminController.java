package com.bayu.employee.controller;

import com.bayu.employee.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final EmployeeService employeeService;

    public AdminController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get All Employee atau bisa cari cara menampilkan list, biasanya sesudah menambahkan data baru, maka akan ditampilkan dalam list
    @GetMapping("/employees")
    public String getAllEmployees(Model model) {

        // looping satu-satu datanya, lalu masukkan kedalam table

        return "";
    }
}
