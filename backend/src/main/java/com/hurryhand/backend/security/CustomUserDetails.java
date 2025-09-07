package com.hurryhand.backend.security;

import com.hurryhand.backend.dto.UserDetailsDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final UserDetailsDTO userDetailsDTO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetailsDTO.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .toList();
    }

    @Override
    public String getPassword() {
        return userDetailsDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetailsDTO.getEmail();
    }

    public String getEmail() {
        return userDetailsDTO.getEmail();
    }

    @Override public boolean isAccountNonExpired()     { return true; }
    @Override public boolean isAccountNonLocked()      { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled()               { return true; }

    public Long getId() {
        return userDetailsDTO.getId();
    }

}