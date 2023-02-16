package com.bayu.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public static final String[] PUBLIC_URLS = {
            "/img/**",
            "/js/**",
            "/css/**",
            "/vendor/**",
            "/webjars/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> {
                            authorize
                                    .antMatchers(PUBLIC_URLS).permitAll()
                                    .antMatchers("/registration").permitAll()
//                                    .antMatchers("/signin").permitAll()
                                    .antMatchers("/admin/**").hasRole("ADMIN")
                                    .antMatchers("/user/**").hasRole("USER")
                                    .antMatchers("/employees/**").hasRole("USER")
                                    .anyRequest().authenticated();
                        }
                )
                .formLogin(form -> form
                        .loginPage("/signin").permitAll()
                )
                .logout(LogoutConfigurer::permitAll
                );

        return http.build();
    }

    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring()
                .antMatchers("/img/**", "/images/**", "/js/**", "/css/**");
    }
}
