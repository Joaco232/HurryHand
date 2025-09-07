package com.hurryhand.backend.security;

import com.hurryhand.backend.dto.UserDetailsDTO;
import com.hurryhand.backend.mappers.BaseUserMapper;
import com.hurryhand.backend.repositories.AdminRepository;
import com.hurryhand.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final BaseUserMapper baseUserMapper;

    @Override
    public UserDetails loadUserByUsername(String email) {

        UserDetailsDTO userDetailsDTO = userRepository.findUserByEmail(email).map(user -> baseUserMapper.toUserDetailsDTO(user))
                .or(() -> adminRepository.findAdminByEmail(email).map(admin -> baseUserMapper.toUserDetailsDTO(admin)))
                .orElseThrow(() -> new BadCredentialsException("Bad credentials"));

        return new CustomUserDetails(userDetailsDTO);
    }




}