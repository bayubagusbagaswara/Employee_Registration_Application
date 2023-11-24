package com.bayu.employee.controller;

import com.bayu.employee.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class FileUploadController {

    private final UploadFileService uploadFileService;

    @GetMapping(path = "/upload")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping(path = "/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
//        try {
//            if (file.getOriginalFilename().endsWith(".csv")) {
//                // Handle CSV File
//            } else if (file.getOriginalFilename().endsWith(".xlsx")) {
//                // Handle Excel file
//            } else if (file.getOriginalFilename().endsWith(".pdf")) {
//                // Handle PDF file
//            } else {
//                model.addAttribute("message", "Unsupported file type");
//            }
//            model.addAttribute("message", "File uploaded successfully");
//        } catch (IOException e) {
//            model.addAttribute("message", "Error processing the file");
//            e.printStackTrace();
//        }
        return "upload";
    }

//    private List<Employee> processCsvData(MultipartFile file) throws IOException {
//        // CSV processing logic (similar to the previous example)
//    }

    private String processExcelData(MultipartFile file) throws IOException {
        // Excel processing logic
        // Use Apache POI or a library like Apache EasyExcel
        return uploadFileService.processExcelData(file);
    }

    // Add methods for processing PDF data if needed

    // TODO: Customize based on your specific requirements
}
