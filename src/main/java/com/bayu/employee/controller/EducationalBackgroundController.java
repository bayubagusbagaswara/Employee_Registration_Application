package com.bayu.employee.controller;

import com.bayu.employee.model.User;
import com.bayu.employee.service.EducationalBackgroundService;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EducationalBackgroundController {

    private final static Logger log = LoggerFactory.getLogger(EducationalBackgroundController.class);

    private final EducationalBackgroundService educationalBackgroundService;
    private final UserService userService;
    private final EmployeeService employeeService;

    public EducationalBackgroundController(EducationalBackgroundService educationalBackgroundService, UserService userService, EmployeeService employeeService) {
        this.educationalBackgroundService = educationalBackgroundService;
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping("/educational")
    public String educationalBackground(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        // saat pertama kita hit menu Educational Background atau URL /educational
        // maka kita akan diarahkan ke halaman educational/home.html
        // dimana di halaman tersebut hanya ada tombol "Tambahkan "

        // cari user berdasarkan username
        User user = userService.findByUsername(username);

        // cek apakah user tersebut sudah memiliki educational background


        return "educational/home.html";
    }
}
