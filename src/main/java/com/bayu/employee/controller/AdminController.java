package com.bayu.employee.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String homeAdmin() {
        return "admin/admin.html";
    }

    @GetMapping("/employees")
    public String getAllEmployees(Authentication authentication, Model model, RedirectAttributes redirectAttributes) {

        // tampilkan table all employees

        return "";
    }

    // bisa get employee by id
    @GetMapping("/employees/{employeeId}")
    public String getEmployeeById(@PathVariable(value = "employeeId") String employeeId,
                                  Authentication authentication,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        // hanya menampilkan data employee by id
        return "";
    }

    // bisa delete employee
    @GetMapping("/employees/delete/{employeeId}")
    public String deleteEmployee(@PathVariable(value = "employeeId") String employeeId,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        // delete employee by id
        return "";
    }

}
