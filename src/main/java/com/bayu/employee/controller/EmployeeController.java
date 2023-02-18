package com.bayu.employee.controller;

import com.bayu.employee.model.Employee;
import com.bayu.employee.payload.employee.CreateEmployeeRequest;
import com.bayu.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Yang bisa dilakukan oleh endpoint ini adalah selain get employee
 */
@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @GetMapping
    public String showFormEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "";
    }

    @PostMapping("/save-employee")
    public String doSaveEmployee(@ModelAttribute("employee") CreateEmployeeRequest createEmployeeRequest,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        log.info("Create Employee: {}", createEmployeeRequest.toString());

        // check validation
        if (bindingResult.hasErrors()){
            return "redirect:/employees";
        }

//        employeeService.saveEmployee();

        return "redirect:/list-employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeById() {
        return "";
    }


    // handle for showing form for update data employee by ID
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") String id,
                                    Model model) {

        // get employee from the service
//        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
//        model.addAttribute("employee", employee);
        return "";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") String id) {

        // call delete employee method
//        employeeService.deleteEmployeeById(id);
        return "";
    }



}
