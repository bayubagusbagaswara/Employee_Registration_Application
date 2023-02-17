package com.bayu.employee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Fungsi WebMvcConfigurer adalah mendaftarkan URL dengan file html pasangannya
 * Misalnya setiap kita akses /index, maka yang akan ditampilkan adalah home.html
 * Jadi tidak lewat HomeController
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login.html");
        registry.addViewController("/signin").setViewName("login.html");
        registry.addViewController("/admin").setViewName("admin.html");
        registry.addViewController("/index").setViewName("index.html");
    }

}
