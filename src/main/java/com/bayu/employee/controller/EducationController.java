package com.bayu.employee.controller;

import com.bayu.employee.model.Employee;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.education.CreateEducationRequest;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.education.UpdateEducationRequest;
import com.bayu.employee.service.EducationService;
import com.bayu.employee.service.EmployeeService;
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
public class EducationController {

    private final static Logger log = LoggerFactory.getLogger(EducationController.class);

    private final EducationService educationService;
    private final UserService userService;
    private final EmployeeService employeeService;

    public EducationController(EducationService educationService, UserService userService, EmployeeService employeeService) {
        this.educationService = educationService;
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping("/education")
    public String education(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        log.info("Username: {}", username);

        User user = userService.findByUsername(username);

        Employee employee = employeeService.findById(user.getId());

        if (employee.getEducations().size() == 0) {
            return "redirect:/education/home";
        }

        List<EducationDTO> educationDTOList = educationService.getAllByEmployeeId(employee.getId());

        String userId = "";

        for (EducationDTO educationDTO : educationDTOList) {
            userId = educationDTO.getUserId();
        }

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("userId", userId);
        return "redirect:/education/user/{userId}";
    }


    @GetMapping("/education/home")
    public String educationHome(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "education/home_education";
    }

    @GetMapping("/education/show-add-form")
    public String showNewEducationForm(Model model, Authentication authentication) {

        String username = authentication.getName();
        User user = userService.findByUsername(username);

        CreateEducationRequest createEducationRequest = new CreateEducationRequest();

        model.addAttribute("createEducationRequest", createEducationRequest);
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", username);

        return "education/add_education";
    }

    @PostMapping("/education/save/{userId}")
    public String saveEducation(@ModelAttribute CreateEducationRequest createEducationRequest,
                                @PathVariable(value = "userId") String userId,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        log.info("Create Education: {}", createEducationRequest.toString());

        if (createEducationRequest.getLevelOfEducation() == null || createEducationRequest.getLevelOfEducation().equals("")) {
            bindingResult.addError(new FieldError("createEducationRequest", "levelOfEducation", "Tingkat Pendidikan wajib diisi."));
        }

        if (createEducationRequest.getDepartment() == null || createEducationRequest.getDepartment().equals("")) {
            bindingResult.addError(new FieldError("createEducationRequest", "department", "Jurusan wajib diisi."));
        }

        if (createEducationRequest.getCollegeName() == null || createEducationRequest.getCollegeName().equals("")) {
            bindingResult.addError(new FieldError("createEducationRequest", "collegeName", "Nama Instansi/Perguruan Tinggi wajib diisi."));
        }

        if (createEducationRequest.getGraduationYear() == null || createEducationRequest.getGraduationYear().equals("")) {
            bindingResult.addError(new FieldError("createEducationRequest", "graduationYear", "Tahun Lulus wajib diisi."));
        }

        if (bindingResult.hasErrors()) {
            return "education/add_education";
        }

        EducationDTO education = educationService.createEducation(userId, createEducationRequest);

        redirectAttributes.addAttribute("userId", userId);

        return "redirect:/education/user/{userId}";
    }

    @GetMapping("/education/user/{userId}")
    public String getAllEducationByUserId(@PathVariable(value = "userId") String userId,
                                          Authentication authentication,
                                          Model model,
                                          RedirectAttributes redirectAttributes) {

        String username = authentication.getName();
        List<EducationDTO> educationList = educationService.getAllByEmployeeId(userId);

        model.addAttribute("educationList", educationList);
        model.addAttribute("username", username);

        return "education/data_education";
    }

    @GetMapping("/education/show-update-form/{educationId}")
    public String showUpdateForm(@PathVariable(value = "educationId") String educationId,
                                 Authentication authentication,
                                 Model model) {

        String username = authentication.getName();

        EducationDTO education = educationService.findById(educationId);

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
    public String doUpdateEducation(@PathVariable(value = "educationId") String educationId,
                                    @ModelAttribute("updateEducationRequest") UpdateEducationRequest updateEducationRequest,
                                    Authentication authentication,
                                    Model model,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        log.info("Update Education: {}", updateEducationRequest.toString());

        String username = authentication.getName();

        if (updateEducationRequest.getLevelOfEducation() == null || updateEducationRequest.getLevelOfEducation().equals("")) {
            bindingResult.addError(new FieldError("updateEducationRequest", "levelOfEducation", "Tingkat Pendidikan wajib diisi."));
        }

        if (updateEducationRequest.getDepartment() == null || updateEducationRequest.getDepartment().equals("")) {
            bindingResult.addError(new FieldError("updateEducationRequest", "department", "Jurusan wajib diisi."));
        }

        if (updateEducationRequest.getCollegeName() == null || updateEducationRequest.getCollegeName().equals("")) {
            bindingResult.addError(new FieldError("updateEducationRequest", "collegeName", "Nama Instansi/Perguruan Tinggi wajib diisi."));
        }

        if (updateEducationRequest.getGraduationYear() == null || updateEducationRequest.getGraduationYear().equals("")) {
            bindingResult.addError(new FieldError("updateEducationRequest", "graduationYear", "Tahun Lulus wajib diisi."));
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/education/show-update-form/{educationId}";
        }

        EducationDTO education = educationService.updateEducation(educationId, updateEducationRequest);

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("userId", education.getUserId());

        return "redirect:/education/user/{userId}";
    }

    @GetMapping("/education/delete/{educationId}")
    public String deleteEducation(@PathVariable(value = "educationId") String educationId,
                                  Authentication authentication,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        EducationDTO educationDTO = educationService.findById(educationId);

        educationService.deleteEducation(educationId);

        redirectAttributes.addAttribute("userId", educationDTO.getUserId());
        model.addAttribute("username", username);

        return "redirect:/education/user/{userId}";
    }

}
