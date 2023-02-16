package com.bayu.employee.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException;

    // loadUserByUsernameOrEmail(String username, String email)

    // loadUserById(String id)
}
