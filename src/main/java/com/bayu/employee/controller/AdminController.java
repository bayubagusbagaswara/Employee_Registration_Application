package com.bayu.employee.controller;

import com.bayu.employee.model.User;
import com.bayu.employee.payload.admin.EmployeeAdminDTO;
import com.bayu.employee.payload.education.EducationDTO;
import com.bayu.employee.payload.employee.EmployeeDTO;
import com.bayu.employee.payload.training.TrainingDTO;
import com.bayu.employee.payload.work.WorkExperienceDTO;
import com.bayu.employee.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;
    private final EmployeeService employeeService;
    private final EducationalBackgroundService educationalBackgroundService;
    private final WorkExperienceService workExperienceService;
    private final TrainingService trainingService;


    public AdminController(UserService userService, AdminService adminService, EmployeeService employeeService, EducationalBackgroundService educationalBackgroundService, WorkExperienceService workExperienceService, TrainingService trainingService) {
        this.userService = userService;
        this.adminService = adminService;
        this.employeeService = employeeService;
        this.educationalBackgroundService = educationalBackgroundService;
        this.workExperienceService = workExperienceService;
        this.trainingService = trainingService;
    }

    @GetMapping("/admin")
    public String homeAdmin() {
        // ini halaman setelah login sebagai admin
        // di halaman ini
        return "admin/admin_home.html";
    }

    @GetMapping("/admin/profile")
    public String adminProfile() {
        return "";
    }

    @GetMapping("/admin/employees")
    public String getAllEmployees(Authentication authentication, Model model, RedirectAttributes redirectAttributes) {

        // tampilkan table all employees
        String username = authentication.getName();

        // cari user by username
        User user = userService.findByUsername(username);

        // ambil semua data employee
        List<EmployeeAdminDTO> employeeList = adminService.getAllEmployees();

        String employeeId = "";

        for (EmployeeAdminDTO employeeAdminDTO : employeeList) {
            employeeId = employeeAdminDTO.getId();
        }

        model.addAttribute("employeeList", employeeList); // isinya hanya position, nik, fullName
        model.addAttribute("username", username);
        redirectAttributes.addAttribute("employeeId", employeeId);

        return "admin/list_employee"; // tampilkan halaman list_employee
    }

    // bisa get employee by id
    @GetMapping("/admin/employees/{employeeId}")
    public String getEmployeeById(@PathVariable(value = "employeeId") String employeeId,
                                  Authentication authentication,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        // cari user by username
        String username = authentication.getName();

        EmployeeDTO employee = adminService.getEmployeeById(employeeId);

        // ambil semua data education by userId
        List<EducationDTO> educationList = adminService.getAllEducationsByEmployeeId(employee.getId());

        // ambil semua data training by userId
        List<TrainingDTO> trainingList = adminService.getAllTrainingsByEmployeeId(employee.getId());

        // ambil semua data work experience by userId
        List<WorkExperienceDTO> workExperienceList = adminService.getAllWorkExperiencesByEmployeeId(employee.getId());

        model.addAttribute("employee", employee);
        model.addAttribute("educationList", educationList);
        model.addAttribute("trainingList", trainingList);
        model.addAttribute("workList", workExperienceList);

        return "admin/data_employee";
    }

    // bisa delete employee
    @GetMapping("/admin/employees/delete/{employeeId}")
    public String deleteEmployee(@PathVariable(value = "employeeId") String employeeId,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        // delete employee by id
        return "redirect:/admin/employees";
    }

    // controller untuk handle pencarian berdasarkan keyword
    // yang terpenting adalah keyword dan property apa

}
