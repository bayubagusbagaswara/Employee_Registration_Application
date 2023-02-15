package com.bayu.employee.controller;

import com.bayu.employee.payload.RegistrationRequest;
import com.bayu.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // view form register
    @GetMapping("/register")
    public String showFormRegistration(@ModelAttribute("registrationRequest") RegistrationRequest registrationRequest,
                                       Model model) {
        // ketika kita get mapping atau showFormRegister,
        // maka dari backend akan menyiapkan mapping (object) yang akan diisi melalui form
        model.addAttribute("registrationRequest", registrationRequest);
        return "auth/register";
    }

    // post data register
    @PostMapping("/register")
    public String doRegistration(RegistrationRequest registrationRequest) {
        log.info(">> registrationRequest : {}", registrationRequest.toString());
        return "auth/register";
    }

    // view form login
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // post data login

}
