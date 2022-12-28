package com.bayu.employee.controller;

import com.bayu.employee.model.Employee;
import com.bayu.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("listEmployees", employees);
        return "index";
    }



}
