package com.hurryhand.backend.services;

import com.hurryhand.backend.dto.admin.CreateAdminDTO;
import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.enums.Role;
import com.hurryhand.backend.exceptions.attribute.EmailAlreadyInUseException;
import com.hurryhand.backend.exceptions.user.UnderAgeUserException;
import com.hurryhand.backend.exceptions.user.UserNotFoundException;
import com.hurryhand.backend.mappers.AdminMapper;
import com.hurryhand.backend.mappers.UserMapper;
import com.hurryhand.backend.models.Admin;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.repositories.AdminRepository;
import com.hurryhand.backend.repositories.UserRepository;
import com.hurryhand.backend.validations.UserValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {


    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator; //crear un adminvalidator
    private final AdminMapper adminMapper;

    @Transactional
    public Admin addNewAdmin(@Valid CreateAdminDTO createAdminDTO) throws UnderAgeUserException, EmailAlreadyInUseException {

        userValidator.validateUniqueEmail(createAdminDTO.getEmail());

        String encodedPassword = passwordEncoder.encode(createAdminDTO.getPassword());
        Admin newAdmin = adminMapper.toEntity(createAdminDTO, encodedPassword);

        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_ADMIN);
        newAdmin.setRoles(roles);

        return adminRepository.save(newAdmin);

    }


}
