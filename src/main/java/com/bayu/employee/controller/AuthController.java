package com.bayu.employee.controller;

import com.bayu.employee.payload.LoginRequest;
import com.bayu.employee.payload.LoginResponse;
import com.bayu.employee.payload.RegistrationRequest;
import com.bayu.employee.payload.RegistrationResponse;
import com.bayu.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/registration")
    public String showFormRegistration(Model model) {

        RegistrationRequest registrationRequest = new RegistrationRequest();
        model.addAttribute("registrationRequest", registrationRequest);
        return "auth/register";
    }

    @PostMapping("/registration")
    public String doRegistration(@Valid @ModelAttribute RegistrationRequest registrationRequest,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        log.info("Registration User: {}", registrationRequest.toString());

        // check username is exists
        if (userService.checkUsernameIsExists(registrationRequest.getUsername())) {
            bindingResult.addError(new FieldError("registrationRequest", "username", "Username is already exists"));
        }

        // check email is exists
        if (userService.checkEmailIsExists(registrationRequest.getEmail())) {
            bindingResult.addError(new FieldError("registrationRequest", "email", "Email is already exists"));
        }

        // check is the password match
        if (registrationRequest.getPassword() != null && registrationRequest.getRepeatPassword() != null) {
            if (!registrationRequest.getPassword().equals(registrationRequest.getRepeatPassword())) {
                bindingResult.addError(new FieldError("registrationRequest", "repeatPassword", "Password must be match"));
            }
        }

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        redirectAttributes.addFlashAttribute("message", "Registration berhasil. Silahkan login!!");

        userService.register(registrationRequest);

        return "redirect:/login";
    }

    // kalau endpoint ini dibah menjadi /signin
    @GetMapping("/signin")
    public String showLoginPage(Model model) {
        model.addAttribute("title", "Login Page");
        return "auth/login";
    }

//    @PostMapping("/login")
//    public String doLogin() {
        // harusnya disini terjadi autentikasi username dan password

//        log.info("Username: {}", loginRequest.getUsername());
//        log.info("Password: {}", loginRequest.getPassword());
//
//        userService.login(loginRequest);

//        return "redirect:/"; // ini mengarah ke register.html
//    }


    // /admin/index
    // /user/index

}
