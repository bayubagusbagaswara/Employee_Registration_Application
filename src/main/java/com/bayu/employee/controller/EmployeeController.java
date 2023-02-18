package com.bayu.employee.controller;

import com.bayu.employee.payload.employee.CreateEmployeeRequest;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.service.EmployeeService;
import com.bayu.employee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Yang bisa dilakukan oleh endpoint ini adalah selain get employee
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;
    private final UserService userService;

    public EmployeeController(EmployeeService employeeService, UserService userService) {
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @GetMapping
    public String myProfile(Model model, Principal principal) {
        // cek apakah user sudah memiliki employee atau belum

        String name = principal.getName();
        // cari user berdasarkan name

        // lalu cari employee berdasarkan id user

        // jika user belum memiliki data employee (employee == null), maka redirect ke "redirect:/employee/show-form-employee"
        // jika user sudah memiliki data employee, maka redirect ke "redirect:/employees/{employeeId}"


        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        model.addAttribute("createEmployeeRequest", createEmployeeRequest);
        return "employee/new_employee";
    }

    @GetMapping("/show-form-employee")
    public String showNewEmployeeForm(Model model) {
        // saat kita get atau mengambil form ini, maka kita buatkan model kosong
        // fungsinya model ini akan dimapping kedalam form nya
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest();
        model.addAttribute("createEmployeeRequest", createEmployeeRequest);
        return "employee/new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("createEmployeeRequest") CreateEmployeeRequest createEmployeeRequest,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        log.info("Create Employee: {}", createEmployeeRequest.toString());

        // check validation
        if (bindingResult.hasErrors()){
            return "redirect:/employees";
        }

        EmployeeDTO employee = employeeService.createEmployee(createEmployeeRequest);

        // jika sukses save employee, maka kita tampilkan halaman data_employee
        // kita ambil object employee, lalu cari employee by id

        redirectAttributes.addAttribute("employeeId", employee.getId());

        return "redirect:/employees/{employeeId}"; // redirect ke getEmployeeById
    }

    @GetMapping("/list-employees")
    public String listEmployees(Model model) {
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("listEmployees", allEmployees);
        return "employee/list_employee";
    }

    @GetMapping("/{employeeId}")
    public String getEmployeeById(@PathVariable(value = "employeeId") String id,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);

        model.addAttribute("employee", employeeDTO);

        return "employee/data_employee";
    }

    @GetMapping("/updateEmployee/{id}")
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

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") String id) {

        // call delete employee method
//        employeeService.deleteEmployeeById(id);
        return "";
    }



}
