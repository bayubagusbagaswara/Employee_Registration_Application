package com.bayu.employee.controller;

import com.bayu.employee.model.Employee;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.employee.CreateEmployeeRequest;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.payload.employee.UpdateEmployeeRequest;
import com.bayu.employee.service.EmployeeService;
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

import static com.bayu.employee.util.ValidationUtil.validationChecksForUpdateEmployeeRequests;

@Controller
public class EmployeeController {

    private final static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;
    private final UserService userService;

    public EmployeeController(EmployeeService employeeService, UserService userService) {
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @GetMapping("/employees")
    public String homeEmployee(Model model,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        User user = userService.findByUsername(username);

        if (user.getEmployee() == null) {
            CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
            model.addAttribute("createEmployeeRequest", createEmployeeRequest);
            return "redirect:/employees/show-add-form";
        }

        Employee employee = employeeService.findByUserId(user.getId());

        redirectAttributes.addAttribute("employeeId", employee.getId());
        model.addAttribute("username", username);

        return "redirect:/employees/{employeeId}";
    }

    @GetMapping("/employees/show-add-form")
    public String showNewEmployeeForm(Model model, Authentication authentication) {

        String username = authentication.getName();

        String name = authentication.getName();
        User user = userService.findByUsername(name);

        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        model.addAttribute("createEmployeeRequest", createEmployeeRequest);
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", username);

        return "employee/add_employee";
    }

    @PostMapping("/employees/save/{userId}")
    public String saveEmployee(@ModelAttribute("createEmployeeRequest") CreateEmployeeRequest createEmployeeRequest,
                               @PathVariable(value = "userId") String userId,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        log.info("Create Employee: {}", createEmployeeRequest.toString());

        // nanti lakukan validation field dulu disini

        if (bindingResult.hasErrors()){
            return "redirect:/employees";
        }

        EmployeeDTO employee = employeeService.createEmployee(userId, createEmployeeRequest);

        redirectAttributes.addAttribute("employeeId", employee.getId());

        return "redirect:/employees/{employeeId}";
    }

    @GetMapping("/employees/{employeeId}")
    public String getEmployeeById(@PathVariable(value = "employeeId") String id,
                                  Authentication authentication,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);

        model.addAttribute("employee", employeeDTO);
        model.addAttribute("username", username);

        return "employee/data_employee";
    }

    @GetMapping("/employees/show-update-form/{employeeId}")
    public String showUpdateForm(@PathVariable(value = "employeeId") String employeeId,
                                 Authentication authentication,
                                 Model model) {

        String username = authentication.getName();

        Employee employee = employeeService.findById(employeeId);

        UpdateEmployeeRequest updateEmployeeRequest = new UpdateEmployeeRequest();
        updateEmployeeRequest.setPosition(employee.getPosition());
        updateEmployeeRequest.setNik(employee.getNik());
        updateEmployeeRequest.setFirstName(employee.getFirstName());
        updateEmployeeRequest.setLastName(employee.getLastName());
        updateEmployeeRequest.setGender(employee.getGender());
        updateEmployeeRequest.setAge(String.valueOf(employee.getAge()));
        updateEmployeeRequest.setPlaceOfBirth(employee.getPlaceOfBirth());
        updateEmployeeRequest.setDateOfBirth(employee.getDateOfBirth());
        updateEmployeeRequest.setSalary(String.valueOf(employee.getSalary()));

        model.addAttribute("updateEmployeeRequest", updateEmployeeRequest);
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("username", username);

        return "employee/edit_employee";
    }

    @PostMapping("/employees/update/{employeeId}")
    public String doUpdateEmployee(@PathVariable(value = "employeeId") String employeeId,
                                   @ModelAttribute("updateEmployeeRequest") UpdateEmployeeRequest updateEmployeeRequest,
                                   Authentication authentication,
                                   Model model,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        log.info("Update Employee: {}", updateEmployeeRequest.toString());

        String username = authentication.getName();

        validationChecksForUpdateEmployeeRequests(updateEmployeeRequest, bindingResult);

        EmployeeDTO employee = employeeService.updateEmployee(employeeId, updateEmployeeRequest);

        model.addAttribute("username", username);
        redirectAttributes.addAttribute("employeeId", employee.getId());

        return "redirect:/employees/{employeeId}";
    }

}