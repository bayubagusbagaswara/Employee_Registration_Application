package com.bayu.employee.controller;

import com.bayu.employee.service.TrainingService;
import com.bayu.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class TrainingController {

    private final static Logger log = LoggerFactory.getLogger(TrainingController.class);

    private final TrainingService trainingService;
    private final UserService userService;

    public TrainingController(TrainingService trainingService, UserService userService) {
        this.trainingService = trainingService;
        this.userService = userService;
    }
}
