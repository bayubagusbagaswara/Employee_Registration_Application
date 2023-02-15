package com.bayu.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {


    // view form register
    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    // post data register

    // view form login
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // post data login

}
