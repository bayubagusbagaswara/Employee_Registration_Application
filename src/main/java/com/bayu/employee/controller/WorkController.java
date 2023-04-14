package com.bayu.employee.controller;

import com.bayu.employee.model.Employee;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.work.WorkExperienceDTO;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.UserService;
import com.bayu.employee.service.WorkExperienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WorkController {

    private final static Logger log = LoggerFactory.getLogger(WorkController.class);

    private final UserService userService;
    private final EmployeeService employeeService;
    private final WorkExperienceService workExperienceService;

    public WorkController(UserService userService, EmployeeService employeeService, WorkExperienceService workExperienceService) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.workExperienceService = workExperienceService;
    }

    @GetMapping("/work")
    public String work(Authentication authentication,
                       Model model,
                       RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        User user = userService.findByUsername(username);

        Employee employee = employeeService.findById(user.getId());

        if (employee.getWorkExperiences().size() == 0) {
            return "redirect:/work/home";
        }

        List<WorkExperienceDTO> workExperienceDTOList = workExperienceService.getAllWorkExperiencesByEmployeeId(user.getId());

//        String userId = "";

        Map<String, String> userMap = new HashMap<>();

        for (WorkExperienceDTO workExperienceDTO : workExperienceDTOList) {
            userMap.put("userId", workExperienceDTO.getUserId());
        }

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("userId", userMap.get("userId"));

        return "redirect:/work/user/{userId}";
    }

}
