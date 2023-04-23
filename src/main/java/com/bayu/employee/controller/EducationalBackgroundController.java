package com.bayu.employee.controller;

import com.bayu.employee.model.User;
import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.education.UpdateEducationRequest;
import com.bayu.employee.service.EducationalBackgroundService;
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

import static com.bayu.employee.util.ValidationUtil.validationChecksForCreateEducationRequest;
import static com.bayu.employee.util.ValidationUtil.validationChecksForUpdateEducationRequests;

@Controller
public class EducationalBackgroundController {

    private final static Logger log = LoggerFactory.getLogger(EducationalBackgroundController.class);

    private final EducationalBackgroundService educationalBackgroundService;
    private final UserService userService;

    public EducationalBackgroundController(EducationalBackgroundService educationalBackgroundService, UserService userService) {
        this.educationalBackgroundService = educationalBackgroundService;
        this.userService = userService;
    }

    @GetMapping("/education")
    public String educationMenu(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        User user = userService.findByUsername(username);

        if (user.getEmployee().getEducationalBackgrounds().size() == 0) {
            return "redirect:/education/home";
        }

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("employeeId", user.getEmployee().getId());

        return "redirect:/education/employee/{employeeId}";
    }


    @GetMapping("/education/home")
    public String educationHome(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "education/home_education";
    }

    @GetMapping("/education/show-add-form")
    public String showAddEducationForm(Model model, Authentication authentication) {

        String username = authentication.getName();
        User user = userService.findByUsername(username);

        CreateEducationRequest createEducationRequest = new CreateEducationRequest();

        model.addAttribute("createEducationRequest", createEducationRequest);
        model.addAttribute("employeeId", user.getEmployee().getId());
        model.addAttribute("username", username);

        return "education/add_education";
    }

    @PostMapping("/education/save/{employeeId}")
    public String saveEducation(@ModelAttribute CreateEducationRequest createEducationRequest,
                                @PathVariable(value = "employeeId") String employeeId,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        log.info("Create Education: {}", createEducationRequest.toString());

        String fieldError = validationChecksForCreateEducationRequest(createEducationRequest, bindingResult);

        if (fieldError != null) return fieldError;

        EducationDTO education = educationalBackgroundService.createEducation(employeeId, createEducationRequest);

        redirectAttributes.addAttribute("employeeId", education.getEmployeeId());

        return "redirect:/education/employee/{employeeId}";
    }

    @GetMapping("/education/employee/{employeeId}")
    public String getAllEducationByEmployeeId(
            @PathVariable(value = "employeeId") String employeeId,
            Authentication authentication,
            Model model,
            RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        List<EducationDTO> educationList = educationalBackgroundService.getAllByEmployeeId(employeeId);

        model.addAttribute("educationList", educationList);
        model.addAttribute("username", username);

        return "education/data_education";
    }

    @GetMapping("/education/show-update-form/{educationId}")
    public String showUpdateEducationForm(
            @PathVariable(value = "educationId") String educationId,
            Authentication authentication,
            Model model) {

        String username = authentication.getName();

        EducationDTO education = educationalBackgroundService.getEducationById(educationId);

        UpdateEducationRequest updateEducationRequest = new UpdateEducationRequest();
        updateEducationRequest.setLevelOfEducation(education.getLevelOfEducation());
        updateEducationRequest.setDepartment(education.getDepartment());
        updateEducationRequest.setCollegeName(education.getCollegeName());
        updateEducationRequest.setGraduationYear(education.getGraduationYear());

        model.addAttribute("updateEducationRequest", updateEducationRequest);
        model.addAttribute("educationId", education.getId());
        model.addAttribute("username", username);

        log.info("Education DTO : {}", education.toString());

        return "education/edit_education";
    }

    @PostMapping("/education/update/{educationId}")
    public String updateEducation(@PathVariable(value = "educationId") String educationId,
                                  @ModelAttribute("updateEducationRequest") UpdateEducationRequest updateEducationRequest,
                                  Authentication authentication,
                                  Model model,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        log.info("Update Education: {}", updateEducationRequest.toString());

        String username = authentication.getName();

        String fieldError = validationChecksForUpdateEducationRequests(updateEducationRequest, bindingResult);

        if (fieldError != null) return fieldError;

        EducationDTO education = educationalBackgroundService.updateEducation(educationId, updateEducationRequest);

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("employeeId", education.getEmployeeId());

        return "redirect:/education/employee/{employeeId}";
    }

    @GetMapping("/education/delete/{educationId}")
    public String deleteEducation(@PathVariable(value = "educationId") String educationId,
                                  Authentication authentication,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        EducationDTO educationDTO = educationalBackgroundService.getEducationById(educationId);

        educationalBackgroundService.deleteEducation(educationId);

        redirectAttributes.addAttribute("employeeId", educationDTO.getEmployeeId());
        model.addAttribute("username", username);

        if (educationalBackgroundService.getAllByEmployeeId(educationDTO.getEmployeeId()).size() == 0) {
            return "redirect:/education/home";
        }
        return "redirect:/education/employee/{employeeId}";
    }

}
