package com.bayu.employee.controller;

import com.bayu.employee.service.UserService;
import com.bayu.employee.service.WorkExperienceService;
import org.springframework.stereotype.Controller;

@Controller
public class WorkController {

    private final UserService userService;
    private final WorkExperienceService workExperienceService;

    public WorkController(UserService userService, WorkExperienceService workExperienceService) {
        this.userService = userService;
        this.workExperienceService = workExperienceService;
    }
}
