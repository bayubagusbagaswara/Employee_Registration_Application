package com.bayu.employee.controller;

import com.bayu.employee.model.User;
import com.bayu.employee.payload.work.CreateWorkExperienceRequest;
import com.bayu.employee.payload.work.UpdateWorkExperienceRequest;
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

    private final static String CREATE_WORK_EXPERIENCE_REQUEST = "createWorkExperienceRequest";
    private final static String UPDATE_WORK_EXPERIENCE_REQUEST = "updateWorkExperienceRequest";
    private final static String FIELD_WORK_EXPERIENCE_POSITION = "position";
    private final static String FIELD_WORK_EXPERIENCE_COMPANY_NAME = "companyName";
    private final static String FIELD_WORK_EXPERIENCE_SALARY = "salary";
    private final static String FIELD_WORK_EXPERIENCE_YEAR_OF_EMPLOYMENT = "yearOfEmployment";
    private final static String FIELD_WORK_EXPERIENCE_YEAR_OF_RESIGNATION = "yearOfResignation";

    private final static String MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_POSITION = "Posisi wajib diisi.";
    private final static String MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_COMPANY_NAME = "Nama Perusahaan wajib diisi.";
    private final static String MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_SALARY = "Gaji wajib diisi.";
    private final static String MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_YEAR_OF_EMPLOYMENT = "Tahun Masuk wajib diisi.";
    private final static String MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_YEAR_OF_RESIGNATION = "Tahun Keluar wajid diisi.";

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
            bindingResult.addError(new FieldError(CREATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_POSITION, MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_POSITION));
        }

        if (createWorkExperienceRequest.getCompanyName().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_COMPANY_NAME, MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_COMPANY_NAME));
        }

        if (createWorkExperienceRequest.getSalary().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_SALARY, MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_SALARY));
        }

        if (createWorkExperienceRequest.getYearOfEmployment().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_YEAR_OF_EMPLOYMENT, MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_YEAR_OF_EMPLOYMENT));
        }

        if (createWorkExperienceRequest.getYearOfResignation().isEmpty()) {
            bindingResult.addError(new FieldError(CREATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_YEAR_OF_RESIGNATION, MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_YEAR_OF_RESIGNATION));
        }

        if (bindingResult.hasErrors()) {
            return "work/add_work";
        }

        return null;
    }

    private static String validationChecksForDataUpdateRequests(UpdateWorkExperienceRequest updateWorkExperienceRequest, BindingResult bindingResult) {

        if (updateWorkExperienceRequest.getPosition().isEmpty()) {
            bindingResult.addError(new FieldError(UPDATE_WORK_EXPERIENCE_REQUEST, FIELD_WORK_EXPERIENCE_POSITION, MESSAGE_VALIDATION_FIELD_WORK_EXPERIENCE_POSITION));
        }
        return null;
    }
}
