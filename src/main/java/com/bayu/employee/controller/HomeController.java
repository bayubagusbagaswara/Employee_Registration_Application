package com.bayu.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // handler for Home Page

    // handler for Home Page, jika User berhasil login
    // ini untuk menggantikan URL default nya "/"
    @GetMapping("/home/user")
    public String homeUser() {
        return "employee/index";
    }
}
