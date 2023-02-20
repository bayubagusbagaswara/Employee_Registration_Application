package com.bayu.employee.controller;

import com.bayu.employee.model.Education;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.service.EducationService;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EducationController {

    private final static Logger log = LoggerFactory.getLogger(EducationController.class);

    private final EducationService educationService;
    private final UserService userService;
    private final EmployeeService employeeService;

    public EducationController(EducationService educationService, UserService userService, EmployeeService employeeService) {
        this.educationService = educationService;
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping("/educational")
    public String educationalBackground(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        User user = userService.findByUsername(username);

        CreateEducationRequest createEducationRequest = new CreateEducationRequest();

        if (user.getEducations() == null) {
            model.addAttribute("createEducationalBackgroundRequest", createEducationRequest);
            return "redirect:/educational/show-form-educational";
        }

        List<Education> educationList = educationService.findByUserId(user.getId());

        String userId = "";

        for (Education education : educationList) {
            userId = education.getUser().getId();
        }

        redirectAttributes.addAttribute("userId", userId);
        return "redirect:/educational/user/{userId}";
    }

    // tampilkan form untuk menambahkan educational background
    @GetMapping("/educational/show-form-educational")
    public String showNewEducationalForm(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        // cari user by username
        String username = authentication.getName();

        return "";
    }
}
