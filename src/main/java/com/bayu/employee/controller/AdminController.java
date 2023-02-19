package com.bayu.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String homeAdmin() {
        return "admin/admin.html";
    }

    // bisa get employee by id

    // bisa get all employee

    // bisa update employee

    // bisa delete employee

}
