package com.bayu.employee.controller;

import com.bayu.employee.model.Employee;
import com.bayu.employee.model.User;
import com.bayu.employee.payload.employee.CreateEmployeeRequest;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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
    public String personalData(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {

        String username = authentication.getName(); // username

        log.info("Name: {}", username);

        // cari user berdasarkan username
        User user = userService.findByUsername(username);

        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();

        // cari employee berdasarkan user_id
        if (user.getEmployee() == null) {
            model.addAttribute("createEmployeeRequest", createEmployeeRequest);
            return "redirect:/employees/show-form-employee";
        }

        Employee employee = employeeService.findByUserId(user.getId());

        // harus redirectAttribute, karena kita langsung redirect ke endpoint
        redirectAttributes.addAttribute("employeeId", employee.getId());
        model.addAttribute("username", username);
        return "redirect:/employees/{employeeId}";
    }

    @GetMapping("/employees/show-form-employee")
    public String showNewEmployeeForm(Model model, Authentication authentication, RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        // cari user by username
        String name = authentication.getName();
        User user = userService.findByUsername(name);

        // redirect user id ke form
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        model.addAttribute("createEmployeeRequest", createEmployeeRequest);
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", username);

        return "employee/new_employee";
    }

    @PostMapping("/employees/saveEmployee/{userId}")
    public String saveEmployee(@Valid @ModelAttribute("createEmployeeRequest") CreateEmployeeRequest createEmployeeRequest,
                                 @PathVariable(value = "userId") String userId,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        log.info("Create Employee: {}", createEmployeeRequest.toString());

        // check validation
        if (bindingResult.hasErrors()){
            return "redirect:/employees";
        }

        EmployeeDTO employee = employeeService.createEmployee(userId, createEmployeeRequest);

        // jika sukses save employee, maka kita tampilkan halaman data_employee
        // kita ambil object employee, lalu cari employee by id

        redirectAttributes.addAttribute("employeeId", employee.getId());

        return "redirect:/employees/{employeeId}"; // redirect ke getEmployeeById
    }

    @GetMapping("/employees/list-employees")
    public String listEmployees(Model model) {
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("listEmployees", allEmployees);
        return "employee/list_employee";
    }

    @GetMapping("/employees/{employeeId}")
    public String getEmployeeById(@PathVariable(value = "employeeId") String id,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);

        model.addAttribute("employee", employeeDTO);

        return "employee/data_employee";
    }

    @GetMapping("/employees/updateEmployee/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") String id,
                                    Model model) {

        // saat kita hit handler get ini
        // maka akan ditampilkan form untuk update employee
        // dimana saat kita hit handler ini juga harus mengirimkan idEmployee

//        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
//        model.addAttribute("employee", employee);
        return "";
    }

    @GetMapping("/employees/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") String id) {

        // call delete employee method
//        employeeService.deleteEmployeeById(id);
        return "";
    }


}