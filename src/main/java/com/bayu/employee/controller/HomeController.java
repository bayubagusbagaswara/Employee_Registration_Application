package com.bayu.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class HomeController {

    @GetMapping
    public String index() {
        return "test/index";
    }

    @GetMapping("/buttons")
    public String buttons() {
        return "test/buttons";
    }

    @GetMapping("/cards")
    public String cards() {
        return "test/cards";
    }

    @GetMapping("/utilities-color")
    public String utilitiesColor() {
        return "test/utilities-color";
    }

    @GetMapping("/utilities-border")
    public String utilitiesBorder() {
        return "test/utilities-border";
    }
    @GetMapping("/utilities-animation")
    public String utilitiesAnimation() {
        return "test/utilities-animation";
    }
    @GetMapping("/utilities-other")
    public String utilitiesOther() {
        return "test/utilities-other";
    }

    @GetMapping("/login")
    public String login() {
        return "test/login";
    }

    @GetMapping("/register")
    public String register() {
        return "test/register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "test/forgot-password";
    }

    @GetMapping("/404")
    public String notFound() {
        return "test/404";
    }

    @GetMapping("/blank")
    public String blank() {
        return "test/blank";
    }

    @GetMapping("/charts")
    public String charts() {
        return "test/charts";
    }

    @GetMapping("/tables")
    public String tables() {
        return "test/tables";
    }

    @GetMapping("/forms")
    public String forms() {
        return "test/forms";
    }

    @GetMapping("/layout")
    public String layout() {
        return "fragments/main_layout";
    }

    @GetMapping("/book")
    public String book() {
        return "test/book";
    }

    @GetMapping("/add-employee")
    public String addEmployee() {
        return "employee/add_employee";
    }

}
