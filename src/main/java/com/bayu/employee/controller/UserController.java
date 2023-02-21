package com.bayu.employee.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class UserController {

    @GetMapping
    public String index(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "user/index.html";
    }

}
