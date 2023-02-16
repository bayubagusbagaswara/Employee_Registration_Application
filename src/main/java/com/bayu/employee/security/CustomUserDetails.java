package com.bayu.employee.security;

import com.bayu.employee.model.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Ini sebagai penghubung (mapping) antara model User yang kita buat sendiri
 * dengan interface UserDetails yang dimiliki oleh Spring Security
 * karena saat autentikasi spring security berdasarkan data UserDetails dan memiliki service UserDetailsService
 * jadi property CustomUserDetails harus sama dengan model User kita
 * tetapi intinya hanya Username, Password, dan authority nya saja yang diperlukan spring security
 */
@Data
@Builder
public class CustomUserDetails implements UserDetails {

    private String id;
    private String username;
    private String email;
    private String password;
    private boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String id, String username, String email, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    // bikin method constructor baru untuk mapping authorities dari model User
    public static CustomUserDetails mapFromUser(User user) {
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().getRoleName()))
                .collect(Collectors.toList());

        return CustomUserDetails.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .enabled(user.isEnabled())
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // cek apakah authorities bernilai null, jika true maka bernilai null
        // jika false artinya ada authorities nya
//        return authorities == null ? null : new ArrayList<>(authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
