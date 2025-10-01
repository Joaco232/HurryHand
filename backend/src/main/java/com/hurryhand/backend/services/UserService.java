package com.hurryhand.backend.services;


import com.hurryhand.backend.auth.AuthResponse;
import com.hurryhand.backend.auth.JwtService;
import com.hurryhand.backend.dto.UserDetailsDTO;
import com.hurryhand.backend.dto.editprofile.*;
import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.dto.user.ProfilePhotoResponseDTO;
import com.hurryhand.backend.dto.user.UserResponseDTO;
import com.hurryhand.backend.enums.Role;
import com.hurryhand.backend.exceptions.attribute.EmailAlreadyInUseException;
import com.hurryhand.backend.exceptions.attribute.PhoneNumberAlreadyInUseException;
import com.hurryhand.backend.exceptions.user.UnderAgeUserException;
import com.hurryhand.backend.exceptions.user.UserNotFoundException;
import com.hurryhand.backend.mappers.BaseUserMapper;
import com.hurryhand.backend.mappers.ProfilePhotoMapper;
import com.hurryhand.backend.mappers.UserMapper;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.repositories.UserRepository;
import com.hurryhand.backend.security.CustomUserDetails;
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
    private final JwtService jwtService;
    private final BaseUserMapper baseUserMapper;
    private final ProfilePhotoMapper profilePhotoMapper;

    @Transactional
    public User addNewUser(@Valid CreateUserDTO createUserDTO) throws UnderAgeUserException,
                                                                        EmailAlreadyInUseException,
                                                                        UserNotFoundException {

        userValidator.validateUniqueEmail(createUserDTO.getEmail());
        userValidator.validateUserAge(createUserDTO.getBirthdate());
        userValidator.validateUniquePhoneNumber(createUserDTO.getPhoneNumber());

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


    public UserResponseDTO getUserByIdForResponse(Long id) throws UserNotFoundException {

        User user = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("El usuario no existe"));

        return userMapper.toUserResponseDTO(user);
    }

    public UserResponseDTO getUserByEmailForResponse(String email) throws UserNotFoundException {

        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("El usuario no existe"));

        return userMapper.toUserResponseDTO(user);
    }


    public User getUserById(Long id) throws UserNotFoundException {

        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("El usuario no existe"));
    }

    public List<UserResponseDTO> getAllUsersForResponse() {

        List<User> users = userRepository.findAll();

        return users.stream().map(user -> userMapper.toUserResponseDTO(user)).toList();
    }

    //EDICION DE USUARIO
    @Transactional
    public void changePhoneNumber(User user, ChangePhoneRequestDTO request) throws PhoneNumberAlreadyInUseException {

        userValidator.validateUniquePhoneNumber(request.getPhoneNumber());

        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);

        //return userMapper.toUserResponseDTO(user);
    }

    @Transactional
    public AuthResponse changeEmail(User user, ChangeEmailRequestDTO request) throws EmailAlreadyInUseException {

        userValidator.validateUniqueEmail(request.getEmail());

        user.setEmail(request.getEmail());
        userRepository.save(user);

        UserDetailsDTO userDetails = baseUserMapper.toUserDetailsDTO(user);
        String newToken = jwtService.generateToken(new CustomUserDetails(userDetails));
        return new AuthResponse(newToken,userMapper.toUserResponseDTO(user));
    }

    @Transactional
    public void changeName(User user, ChangeNameRequestDTO request) {

        user.setName(request.getName());
        userRepository.save(user);

    }

    @Transactional
    public void changeSurname(User user, ChangeSurnameRequestDTO request) {

        user.setSurname(request.getSurname());
        userRepository.save(user);

    }

    @Transactional
    public void changeProfilePhoto(User user, String photoUrl){

        user.setProfilePhoto(photoUrl);
        userRepository.save(user);

    }


    @Transactional
    public void changePassword(User user, ChangePasswordRequestDTO request) {

        userValidator.validatePasswordMatches(request.getCurrentPassword(),user.getPassword());
        // mierda
        String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(encodedNewPassword);
        userRepository.save(user);

        //return userMapper.toUserResponseDTO(user);
    }





}
