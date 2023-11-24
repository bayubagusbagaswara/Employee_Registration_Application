package com.bayu.employee.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {

    String processExcelData(MultipartFile file) throws IOException;

}
