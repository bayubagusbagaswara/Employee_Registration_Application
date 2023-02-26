package com.bayu.employee.controller;

import com.bayu.employee.model.User;
import com.bayu.employee.payload.training.CreateTrainingRequest;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.service.TrainingService;
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
public class TrainingController {

    private final static Logger log = LoggerFactory.getLogger(TrainingController.class);

    private final TrainingService trainingService;
    private final UserService userService;

    public TrainingController(TrainingService trainingService, UserService userService) {
        this.trainingService = trainingService;
        this.userService = userService;
    }

    @GetMapping("/training")
    public String training(Authentication authentication,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        User user = userService.findByUsername(username);

        if (user.getTrainings().size() == 0) {
            return "redirect:/training/home";
        }

        List<TrainingDTO> trainingDTOList = trainingService.getAllTrainingsByUserId(user.getId());

        String userId = "";

        for (TrainingDTO trainingDTO : trainingDTOList) {
            userId = trainingDTO.getUserId();
        }

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("userId", userId);

        return "redirect:/training/user/userId";
    }

    @GetMapping("/training/home")
    public String trainingHome(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "training/home_training";
    }

    @GetMapping("/training/show-add-form")
    public String showAddTrainingForm(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        CreateTrainingRequest createTrainingRequest = new CreateTrainingRequest();

        model.addAttribute("createTrainingRequest", createTrainingRequest);
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", username);

        return "training/add_training";
    }


}
