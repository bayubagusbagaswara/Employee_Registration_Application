package com.bayu.employee.controller;

import com.bayu.employee.model.User;
import com.bayu.employee.payload.training.CreateTrainingRequest;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.training.UpdateTrainingRequest;
import com.bayu.employee.service.TrainingHistoryService;
import com.bayu.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.bayu.employee.util.ValidationUtil.validationChecksForCreateTrainingRequests;
import static com.bayu.employee.util.ValidationUtil.validationChecksForUpdateTrainingRequests;

@Controller
public class TrainingHistoryController {

    private final static Logger log = LoggerFactory.getLogger(TrainingHistoryController.class);

    private final TrainingHistoryService trainingHistoryService;
    private final UserService userService;

    public TrainingHistoryController(TrainingHistoryService trainingHistoryService, UserService userService) {
        this.trainingHistoryService = trainingHistoryService;
        this.userService = userService;
    }

    @GetMapping("/training")
    public String trainingMenu(Authentication authentication, Model model, RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        User user = userService.findByUsername(username);

        if (user.getEmployee().getTrainingHistories().size() == 0) {
            return "redirect:/training/home";
        }

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("employeeId", user.getEmployee().getId());

        return "redirect:/training/employee/{employeeId}";
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
        model.addAttribute("employeeId", user.getEmployee().getId());
        model.addAttribute("username", username);

        return "training/add_training";
    }

    @PostMapping("/training/save/{employeeId}")
    public String saveTraining(@ModelAttribute CreateTrainingRequest createTrainingRequest,
                               @PathVariable(value = "employeeId") String employeeId,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        log.info("Create Training : {}", createTrainingRequest.toString());

        String fieldError = validationChecksForCreateTrainingRequests(createTrainingRequest, bindingResult);

        if (fieldError != null) return fieldError;

        TrainingDTO training = trainingHistoryService.createTraining(employeeId, createTrainingRequest);

        redirectAttributes.addAttribute("employeeId", training.getEmployeeId());

        return "redirect:/training/employee/{employeeId}";
    }

    @GetMapping("/training/employee/{employeeId}")
    public String getAllTrainingByEmployeeId(
            @PathVariable(value = "employeeId") String employeeId,
            Authentication authentication,
            Model model,
            RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        List<TrainingDTO> trainingList = trainingHistoryService.getAllTrainingsByEmployeeId(employeeId);

        model.addAttribute("trainingList", trainingList);
        model.addAttribute("username", username);

        return "training/data_training";
    }

    @GetMapping("/training/show-update-form/{trainingId}")
    public String showUpdateTrainingHistoryForm(
            @PathVariable(value = "trainingId") String trainingId,
            Authentication authentication,
            Model model) {

        String username = authentication.getName();

        TrainingDTO training = trainingHistoryService.getTrainingById(trainingId);

        UpdateTrainingRequest updateTrainingRequest = new UpdateTrainingRequest();
        updateTrainingRequest.setTrainingName(training.getTrainingName());
        updateTrainingRequest.setCertificate(training.getCertificate());
        updateTrainingRequest.setYear(training.getYear());

        model.addAttribute("updateTrainingRequest", updateTrainingRequest);
        model.addAttribute("trainingId", training.getId());
        model.addAttribute("username", username);

        log.info("Training DTO: {}", training.toString());

        return "training/edit_training";
    }

    @PostMapping("/training/update/{trainingId}")
    public String doUpdateTraining(@PathVariable(value = "trainingId") String trainingId,
                                   @ModelAttribute("updateTrainingRequest") UpdateTrainingRequest updateTrainingRequest,
                                   Authentication authentication,
                                   Model model,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        log.info("Update Training: {}", updateTrainingRequest.toString());

        String username = authentication.getName();

        String fieldError = validationChecksForUpdateTrainingRequests(updateTrainingRequest, bindingResult);

        if (fieldError != null) return fieldError;

        TrainingDTO training = trainingHistoryService.updateTraining(trainingId, updateTrainingRequest);

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("employeeId", training.getEmployeeId());

        return "redirect:/training/employee/{employeeId}";
    }

    @GetMapping("/training/delete/{trainingId}")
    public String deleteTraining(@PathVariable(value = "trainingId") String trainingId,
                                 Authentication authentication,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        TrainingDTO trainingDTO = trainingHistoryService.getTrainingById(trainingId);

        trainingHistoryService.deleteTraining(trainingId);

        redirectAttributes.addAttribute("employeeId", trainingDTO.getEmployeeId());
        model.addAttribute("username", username);

        if (trainingHistoryService.getAllTrainingsByEmployeeId(trainingDTO.getEmployeeId()).size() == 0) {
            return "redirect:/training/home";
        }

        return "redirect:/training/employee/{employeeId}";
    }

}
