package com.bayu.employee.controller;

import com.bayu.employee.model.Employee;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.training.CreateTrainingRequest;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.training.UpdateTrainingRequest;
import com.bayu.employee.service.EmployeeService;
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
public class TrainingController {

    private final static Logger log = LoggerFactory.getLogger(TrainingController.class);

    private final TrainingService trainingService;
    private final UserService userService;
    private final EmployeeService employeeService;

    public TrainingController(TrainingService trainingService, UserService userService, EmployeeService employeeService) {
        this.trainingService = trainingService;
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping("/training")
    public String training(Authentication authentication,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        User user = userService.findByUsername(username);

        // cari employee berdasarkan id, karena employeeId sama dengan userId, maka tinggal cari findById
        Employee employee = employeeService.findById(user.getId());

        // ini tetap, tapi melalui employee dulu
        if (employee.getTrainings().size() == 0) {
            return "redirect:/training/home";
        }

        List<TrainingDTO> trainingDTOList = trainingService.getAllTrainingsByEmployeeId(user.getId());

        String userId = "";

        for (TrainingDTO trainingDTO : trainingDTOList) {
            userId = trainingDTO.getUserId();
        }

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("userId", userId);

        return "redirect:/training/user/{userId}";
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

    @PostMapping("/training/save/{userId}")
    public String saveTraining(@ModelAttribute CreateTrainingRequest createTrainingRequest,
                               @PathVariable(value = "userId") String userId,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        log.info("Create Training : {}", createTrainingRequest.toString());

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

        TrainingDTO training = trainingService.createTraining(userId, createTrainingRequest);

        redirectAttributes.addAttribute("userId", userId);

        return "redirect:/training/user/{userId}";
    }

    @GetMapping("/training/user/{userId}")
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

        TrainingDTO trainingDTO = trainingService.getTrainingById(trainingId);

        trainingService.deleteTraining(trainingId);

        redirectAttributes.addAttribute("userId", trainingDTO.getId());
        model.addAttribute("username", username);

        return "redirect:/training/user/{userId}";
    }

}
