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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/education")
    public String education(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        User user = userService.findByUsername(username);

        if (user.getEducations().size() == 0) {
            // ini diarahakan ke home_education
            return "redirect:/education/home";
        }

        List<Education> educationList = educationService.findByUserId(user.getId());

        String userId = "";

        for (Education education : educationList) {
            userId = education.getUser().getId();
        }

        // jika sudah ada education minimal 1
        redirectAttributes.addAttribute("userId", userId);
        return "redirect:/education/user/{userId}";
    }


    @GetMapping("/education/home")
    public String educationHome(Model model) {
        model.addAttribute("homeEducation", "Home Education");
        return "education/home_education";
    }

    @GetMapping("/education/show-form-education")
    public String showNewEducationForm(Model model, Authentication authentication) {

        String username = authentication.getName();
        User user = userService.findByUsername(username);

        CreateEducationRequest createEducationRequest = new CreateEducationRequest();

        model.addAttribute("createEducationRequest", createEducationRequest);
        model.addAttribute("userId", user.getId());

        return "education/add_education";
    }

    @GetMapping("/education/user/{userId}")
    public String getAllEducationByUserId(@PathVariable(value = "userId") String userId,
                                          Model model,
                                          RedirectAttributes redirectAttributes) {

        List<Education> educationList = educationService.findByUserId(userId);

        model.addAttribute("educationList", educationList);

        return "education/data_education"; // redirect ke halaman education/data_education.html (disini data berbentuk tabel)
    }
}
