package com.hurryhand.backend.services;


import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.enums.Role;
import com.hurryhand.backend.exceptions.attribute.EmailAlreadyInUseException;
import com.hurryhand.backend.exceptions.user.UnderAgeUserException;
import com.hurryhand.backend.exceptions.user.UserNotFoundException;
import com.hurryhand.backend.mappers.UserMapper;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.repositories.UserRepository;
import com.hurryhand.backend.validations.UserValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;
    private final UserMapper userMapper;

    @Transactional
    public User addNewUser(@Valid CreateUserDTO createUserDTO) throws UnderAgeUserException, EmailAlreadyInUseException {

        userValidator.validateUniqueEmail(createUserDTO.getEmail());
        userValidator.validateUserAge(createUserDTO.getBirthdate());

        String encodedPassword = passwordEncoder.encode(createUserDTO.getPassword());
        User newUser = userMapper.toEntity(createUserDTO,  encodedPassword);

        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_USER);
        newUser.setRoles(roles);

        return userRepository.save(newUser);
    }


    public boolean existsUserByEmail(String email) {

        return userRepository.existsUserByEmail(email);
    }

    public boolean existsUserById(Long id) {

        return userRepository.existsUserById(id);
    }


    public User getUserById(Long id) throws UserNotFoundException {

        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("El usuario no existe"));
    }
}
