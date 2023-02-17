package com.bayu.employee.controller;

import com.bayu.employee.service.EmployeeService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    private final EmployeeService employeeService;

    public AdminController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/admin")
    public String homeAdmin() {
        // mengarah ke admin.html
        // misal kita coba tidak masuk kedalam folder admin, karena kita simpan admin.html didalam folder admin
        return "admin/admin.html";
    }

}
