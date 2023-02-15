package com.bayu.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // handler for Home Page

    // handler for Home Page, jika User berhasil login
    @GetMapping("/home/user")
    public String homeUser() {
        return "employee/index";
    }
}
