package com.bayu.employee.service.impl;

import com.bayu.employee.model.User;
import com.bayu.employee.repository.UserRepository;
import com.bayu.employee.security.CustomUserDetails;
import com.bayu.employee.service.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // disini kita nanti juga ambil role tiap user nya, lalu cek role nya apa
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with this username : %s", username)));

        return CustomUserDetails.mapFromUser(user);
    }

}
