package com.bayu.employee.controller;

import com.bayu.employee.model.User;
import com.bayu.employee.payload.training.CreateTrainingRequest;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.training.UpdateTrainingRequest;
import com.bayu.employee.service.TrainingService;
import com.bayu.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TrainingHistoryController {

    private final static Logger log = LoggerFactory.getLogger(TrainingHistoryController.class);

    private final TrainingService trainingService;
    private final UserService userService;

    public TrainingHistoryController(TrainingService trainingService, UserService userService) {
        this.trainingService = trainingService;
        this.userService = userService;
    }

    @GetMapping("/training")
    public String trainingMenu(Authentication authentication, Model model, RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        User user = userService.findByUsername(username);

        if (user.getEmployee().getTrainings().size() == 0) {
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

    @PostMapping("/training/employee/{employeeId}")
    public String saveTraining(@ModelAttribute CreateTrainingRequest createTrainingRequest,
                               @PathVariable(value = "employeeId") String employeeId,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        log.info("Create Training : {}", createTrainingRequest.toString());

        String fieldError = checkValidation(createTrainingRequest, bindingResult);
        if (fieldError != null) return fieldError;

        TrainingDTO training = trainingService.createTraining(employeeId, createTrainingRequest);

        redirectAttributes.addAttribute("employeeId", training.getEmployeeId());

        return "redirect:/training/employee/{employeeId}";
    }

    private static String checkValidation(CreateTrainingRequest createTrainingRequest, BindingResult bindingResult) {
        if (createTrainingRequest.getTrainingName() == null) {
            bindingResult.addError(new FieldError("createTrainingRequest", "trainingName", "Nama Pelatihan wajib diisi."));
        }

        if (createTrainingRequest.getCertificate() == null) {
            bindingResult.addError(new FieldError("createTrainingRequest", "certificate", "Sertifikat wajib diisi."));
        }

        if (createTrainingRequest.getYear() == null) {
            bindingResult.addError(new FieldError("createTrainingRequest", "year", "Tahun wajib diisi."));
        }

        if (bindingResult.hasErrors()) {
            return "training/add_training";
        }
        return null;
    }

    @GetMapping("/training/employee/{employeeId}")
    public String getAllTrainingByUserId(@PathVariable(value = "userId") String userId,
                                         Authentication authentication,
                                         Model model,
                                         RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        List<TrainingDTO> trainingList = trainingService.getAllTrainingsByEmployeeId(userId);

        model.addAttribute("trainingList", trainingList);
        model.addAttribute("username", username);

        return "training/data_training";
    }

    @GetMapping("/training/show-update-form/{trainingId}")
    public String showUpdateForm(@PathVariable(value = "trainingId") String trainingId,
                                 Authentication authentication,
                                 Model model) {

        String username = authentication.getName();

        TrainingDTO training = trainingService.getTrainingById(trainingId);

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

        if (updateTrainingRequest.getTrainingName() == null) {
            bindingResult.addError(new FieldError("updateTrainingRequest", "trainingName", "Nama Pelatihan wajib diisi."));
        }

        if (updateTrainingRequest.getCertificate() == null) {
            bindingResult.addError(new FieldError("updateTrainingRequest", "certificate", "Sertifikat wajib diisi."));
        }

        if (updateTrainingRequest.getYear() == null) {
            bindingResult.addError(new FieldError("updateTrainingRequest", "year", "Tahun wajib diisi."));
        }

        TrainingDTO training = trainingService.updateTraining(trainingId, updateTrainingRequest);

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("userId", training.getUserId());

        return "redirect:/training/user/{userId}";
    }

    @GetMapping("/training/delete/{trainingId}")
    public String deleteTraining(@PathVariable(value = "trainingId") String trainingId,
                                 Authentication authentication,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        String username = authentication.getName();

        User user = userService.findByUsername(username);

        trainingService.deleteTraining(trainingId);

        redirectAttributes.addAttribute("userId", user.getId());
        model.addAttribute("username", username);

        // cek dulu jika setelah dihapus masih ada data, maka redirect ke /training/user/{userId}
        // jika datanya sudah kosong maka redirect ke /training/home
        return "redirect:/training/user/{userId}";
    }

}
