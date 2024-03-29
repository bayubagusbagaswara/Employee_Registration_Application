package com.bayu.employee.controller;

import com.bayu.employee.model.User;
import com.bayu.employee.payload.work.CreateWorkExperienceRequest;
import com.bayu.employee.payload.work.UpdateWorkExperienceRequest;
import com.bayu.employee.payload.work.WorkExperienceDTO;
import com.bayu.employee.service.UserService;
import com.bayu.employee.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
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

import static com.bayu.employee.util.ValidationUtil.validationChecksForCreateWorkExperienceRequests;
import static com.bayu.employee.util.ValidationUtil.validationChecksForUpdateWorkExperienceRequests;

@Controller
@RequiredArgsConstructor
public class WorkExperienceController {

    private static final Logger log = LoggerFactory.getLogger(WorkExperienceController.class);

    private final UserService userService;

    private final WorkExperienceService workExperienceService;

    private static final String REDIRECT_URL = "redirect:";
    private static final String WORK_URL = "/work";

    private static final String EMPLOYEE_URL = "/employee";

    private static final String WORK_DIRECTORY = "work";

    private static final String USERNAME = "username";

    private static final String EMPLOYEE_ID = "employeeId";

    @GetMapping("/work")
    public String workExperienceMenu(Authentication authentication,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        User user = userService.findByUsername(username);

        if (user.getUserInformation().getWorkExperiences().isEmpty()) {
            return "redirect:/work/home";
        }

        model.addAttribute(USERNAME, username);
        redirectAttributes.addAttribute(EMPLOYEE_ID, user.getUserInformation().getId());

//        return "redirect:/work/employee/{employeeId}";
        return REDIRECT_URL.concat(WORK_URL).concat(EMPLOYEE_URL).concat("/{employeeId}");
    }

    @GetMapping("/work/home")
    public String workExperienceHome(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute(USERNAME, username);
//        return "work/home_work";
        return WORK_DIRECTORY.concat("/home_work");
    }

    @GetMapping("/work/show-add-form")
    public String showAddWorkExperienceForm(Model model, Authentication authentication) {

        String username = authentication.getName();

        User user = userService.findByUsername(username);

        CreateWorkExperienceRequest createWorkExperienceRequest = new CreateWorkExperienceRequest();

        // lengthOfWork is not exist on object createWorkExperienceRequest
        // because that is calculate from yearOfResignation and yearOfEmployment
        // do calculate process in service layer

        model.addAttribute("createWorkExperienceRequest", createWorkExperienceRequest);
        model.addAttribute(EMPLOYEE_ID, user.getUserInformation().getId());
        model.addAttribute(USERNAME, username);

//        return "work/add_work";
        return WORK_DIRECTORY.concat("/add_work");
    }

    @PostMapping("/work/save/{employeeId}")
    public String saveWorkExperience(@ModelAttribute CreateWorkExperienceRequest createWorkExperienceRequest,
                                     @PathVariable(value = "employeeId") String employeeId,
                                     BindingResult bindingResult,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {

        log.info("Create Work Experience : {}", createWorkExperienceRequest.toString());

        String fieldError = validationChecksForCreateWorkExperienceRequests(createWorkExperienceRequest, bindingResult);

        if (fieldError != null) return fieldError;

        WorkExperienceDTO workExperience = workExperienceService.createWorkExperience(employeeId, createWorkExperienceRequest);

        redirectAttributes.addAttribute(EMPLOYEE_ID, workExperience.getEmployeeId());

        return "redirect:/work/employee/{employeeId}";
    }

    @GetMapping("/work/employee/{employeeId}")
    public String getAllWorkExperienceEmployeeId(@PathVariable(value = "employeeId") String employeeId,
                                                 Authentication authentication,
                                                 Model model,
                                                 RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        List<WorkExperienceDTO> workExperienceList = workExperienceService.getAllWorkExperiencesByEmployeeId(employeeId);

        model.addAttribute("workList", workExperienceList);
        model.addAttribute(USERNAME, username);

        return "work/data_work";
    }

    @GetMapping("/work/show-update-form/{workExperienceId}")
    public String showUpdateWorkExperienceForm(@PathVariable(value = "workExperienceId") String workExperienceId,
                                               Authentication authentication,
                                               Model model) {

        String username = authentication.getName();

        WorkExperienceDTO workExperience = workExperienceService.getWorkExperienceById(workExperienceId);

        UpdateWorkExperienceRequest updateWorkExperienceRequest = UpdateWorkExperienceRequest.builder()
                .position(workExperience.getPosition())
                .companyName(workExperience.getCompanyName())
                .salary(workExperience.getSalary())
                .yearOfEmployment(workExperience.getYearOfEmployment())
                .yearOfResignation(workExperience.getYearOfResignation())
                .build();

        model.addAttribute("updateWorkExperienceRequest", updateWorkExperienceRequest);
        model.addAttribute("workExperienceId", workExperience.getId());
        model.addAttribute(USERNAME, username);

        log.info("Work Experience DTO : {}", workExperience.toString());

        return "work/edit_work";
    }

    @PostMapping("/work/update/{workExperienceId}")
    public String updateWorkExperience(@PathVariable(value = "workExperienceId") String workExperienceId,
                                       @ModelAttribute("updateWorkExperienceRequest") UpdateWorkExperienceRequest updateWorkExperienceRequest,
                                       Authentication authentication,
                                       Model model,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        log.info("Update Work Experience : {}", updateWorkExperienceRequest.toString());

        String username = authentication.getName();

        String fieldError = validationChecksForUpdateWorkExperienceRequests(updateWorkExperienceRequest, bindingResult);

        if (fieldError != null) return fieldError;

        WorkExperienceDTO workExperienceDTO = workExperienceService.updateWorkExperience(workExperienceId, updateWorkExperienceRequest);

        model.addAttribute(USERNAME, username);
        redirectAttributes.addAttribute(EMPLOYEE_ID, workExperienceDTO.getEmployeeId());

        return "redirect:/work/employee/{employeeId}";
    }


    @GetMapping("/work/delete/{workExperienceId}")
    public String deleteWorkExperience(@PathVariable(value = "workExperienceId") String workExperienceId,
                                       Authentication authentication,
                                       Model model,
                                       RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        WorkExperienceDTO workExperienceDTO = workExperienceService.getWorkExperienceById(workExperienceId);

        workExperienceService.deleteWorkExperience(workExperienceDTO.getId());

        redirectAttributes.addAttribute(EMPLOYEE_ID, workExperienceDTO.getEmployeeId());
        model.addAttribute(USERNAME, username);

        if (workExperienceService.getAllWorkExperiencesByEmployeeId(workExperienceDTO.getEmployeeId()).isEmpty()) {
            return "redirect:/work/home";
        }

        return "redirect:/work/employee/{employeeId}";
    }

}
