package com.bayu.employee.controller;

import com.bayu.employee.payload.RegistrationRequest;
import com.bayu.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/register")
    public String showFormRegistration(@ModelAttribute("registrationRequest") RegistrationRequest registrationRequest,
                                       Model model) {

        model.addAttribute("registrationRequest", registrationRequest);
        return "auth/register";
    }

    @PostMapping("/register")
    public String doRegistration(@Valid RegistrationRequest registrationRequest,
                                 BindingResult bindingResult,
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

        redirectAttributes.addFlashAttribute("message", "Success! your registration is now complete");

        userService.register(registrationRequest);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/login/success")
    public String loginSuccess() {
        return "Login Success!!";
    }

    // /admin/index
    // /user/index

}
