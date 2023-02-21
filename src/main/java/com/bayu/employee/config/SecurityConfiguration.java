package com.bayu.employee.config;

import com.bayu.employee.security.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                .csrf().disable()
                .authorizeHttpRequests(authorize -> {
                            authorize
                                    .antMatchers(PUBLIC_URLS).permitAll()
                                    .antMatchers("/registration").permitAll()
                                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                                    .antMatchers("/index/**").hasAuthority("USER")
                                    .antMatchers("/employees/**").hasAuthority("USER")
                                    .antMatchers("/education/**").hasAuthority("USER")
                                    .antMatchers("/training/**").hasAuthority("USER")
                                    .antMatchers("/work/**").hasAuthority("USER")
                                    .antMatchers("/test/**").permitAll()
                                    .anyRequest().authenticated();
                        }
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
                .antMatchers("/img/**", "/images/**", "/js/**", "/css/**");
    }
}
