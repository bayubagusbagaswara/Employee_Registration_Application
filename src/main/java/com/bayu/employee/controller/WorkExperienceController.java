package com.bayu.employee.controller;

import com.bayu.employee.model.User;
import com.bayu.employee.payload.work.CreateWorkExperienceRequest;
import com.bayu.employee.service.UserService;
import com.bayu.employee.service.WorkExperienceService;
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

@Controller
public class WorkExperienceController {

    private final static Logger log = LoggerFactory.getLogger(WorkExperienceController.class);

    private final UserService userService;
    private final WorkExperienceService workExperienceService;

    public WorkExperienceController(UserService userService, WorkExperienceService workExperienceService) {
        this.userService = userService;
        this.workExperienceService = workExperienceService;
    }

    @GetMapping("/work")
    public String workExperienceMenu(Authentication authentication,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        User user = userService.findByUsername(username);

        if (user.getEmployee().getWorkExperiences().size() == 0) {
            return "redirect:/work/home";
        }

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("employeeId", user.getEmployee().getId());

        return "redirect:/work/employee/{employeeId}";
    }

    @GetMapping("/work/home")
    public String workExperienceHome(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "work/home_work";
    }

    @GetMapping("/work/show-add-form")
    public String showAddWorkExperienceForm(Model model, Authentication authentication) {

        String username = authentication.getName();

        User user = userService.findByUsername(username);

        CreateWorkExperienceRequest createWorkExperienceRequest = new CreateWorkExperienceRequest();

        model.addAttribute("createWorkExperienceRequest", createWorkExperienceRequest);
        model.addAttribute("employeeId", user.getEmployee().getId());
        model.addAttribute("username", username);

        return "work/add_work";
    }

    @PostMapping("/work/save/{employeeId}")
    public String saveWorkExperience(@ModelAttribute CreateWorkExperienceRequest createWorkExperienceRequest,
                                     @PathVariable(value = "employeeId") String employeeId,
                                     BindingResult bindingResult,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {

        log.info("Create Work Experience : {}", createWorkExperienceRequest.toString());



        return null;
    }

    private static String validationCheck(CreateWorkExperienceRequest createWorkExperienceRequest, BindingResult bindingResult) {
        if (createWorkExperienceRequest.getPosition().isEmpty()) {
            bindingResult.addError(new FieldError("createWorkExperienceRequest", "position", "Posisi wajib diisi."));
        }

        if (createWorkExperienceRequest.getCompanyName().isEmpty()) {
            bindingResult.addError(new FieldError("createWorkExperienceRequest", "companyName", "Nama Perusahaan wajib diisi."));
        }

        if (createWorkExperienceRequest.getSalary().isEmpty()) {
            bindingResult.addError(new FieldError("createWorkExperienceRequest", "salary", "Gaji wajib diisi."));
        }

        if (createWorkExperienceRequest.getYearOfEmployment().isEmpty()) {
            bindingResult.addError(new FieldError("createWorkExperienceRequest", "yearOfEmployment", "Tahun Masuk wajib diisi."));
        }

        if (createWorkExperienceRequest.getYearOfResignation().isEmpty()) {
            bindingResult.addError(new FieldError("createWorkExperienceRequest", "yearOfResignation", "Tahun Keluar wajib diisi."));
        }

        if (bindingResult.hasErrors()) {
            return "work/add_work";
        }

        return null;
    }
}
