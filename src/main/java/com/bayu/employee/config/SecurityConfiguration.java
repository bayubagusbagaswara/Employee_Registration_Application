package com.bayu.employee.config;

import com.bayu.employee.security.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfiguration(CustomAuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

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
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/registration")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAuthority("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/index/**")).hasAuthority("USER")
                        .requestMatchers(new AntPathRequestMatcher("/employees/**")).hasAuthority("USER")
                        .requestMatchers(new AntPathRequestMatcher("/education/**")).hasAuthority("USER")
                        .requestMatchers(new AntPathRequestMatcher("/training/**")).hasAuthority("USER")
                        .requestMatchers(new AntPathRequestMatcher("/work/**")).hasAuthority("USER")
                        .requestMatchers(new AntPathRequestMatcher("/test/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/signin")
                        .successHandler(authenticationSuccessHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                );

        return http.build();
    }

    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring()
                .requestMatchers("/img/**", "/images/**", "/js/**", "/css/**");
    }
}
