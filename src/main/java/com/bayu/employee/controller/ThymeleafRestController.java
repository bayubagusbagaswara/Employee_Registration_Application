package com.bayu.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RestController
public class ThymeleafRestController {

    private final TemplateEngine templateEngine;

    @Autowired
    public ThymeleafRestController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @GetMapping(path = "/api/html-example")
    public ResponseEntity<String> getHtmlExample() {
        // Create a Thymeleaf context
        Context context = new Context();
        context.setVariable("message", "Hello from Thymeleaf in a RESTful endpoint!");

        // Process the Thymeleaf template
        String htmlContent = templateEngine.process("htmlExample", context);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }
}
