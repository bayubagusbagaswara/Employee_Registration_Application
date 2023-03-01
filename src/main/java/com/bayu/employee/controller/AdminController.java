package com.bayu.employee.controller;

import com.bayu.employee.model.User;
import com.bayu.employee.payload.admin.EmployeeAdminDTO;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.service.AdminService;
import com.bayu.employee.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;

    public AdminController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @GetMapping
    public String homeAdmin() {
        return "admin/admin.html";
    }

    @GetMapping("/employees")
    public String getAllEmployees(Authentication authentication, Model model, RedirectAttributes redirectAttributes) {

        // tampilkan table all employees
        String username = authentication.getName();

        // cari user by username
        User user = userService.findByUsername(username);

        // ambil semua data employee
        List<EmployeeAdminDTO> employeeList = adminService.getAllEmployees();

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("username", username);


        return "admin/list_employee"; // tampilkan halaman list_employee
    }

    // bisa get employee by id
    @GetMapping("/employees/{employeeId}")
    public String getEmployeeById(@PathVariable(value = "employeeId") String employeeId,
                                  Authentication authentication,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        // cari user by username
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        String userId = user.getId();

        // dapatkan employee by id
        EmployeeDTO employee = adminService.getEmployeeById(employeeId);

        model.addAttribute("employee", employee);
        redirectAttributes.addAttribute("userId", userId);

        // hanya menampilkan data employee by id
        return "admin/data_employee"; // tampilkan halaman employee
    }

    // bisa delete employee
    @GetMapping("/employees/delete/{employeeId}")
    public String deleteEmployee(@PathVariable(value = "employeeId") String employeeId,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        // delete employee by id
        return "redirect:/admin/employees";
    }

}
