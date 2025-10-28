package com.hurryhand.backend.controllers.REST;


import com.hurryhand.backend.auth.AuthResponse;
import com.hurryhand.backend.decorators.AddNewUserDoc;
import com.hurryhand.backend.decorators.GetUserByIdDoc;
import com.hurryhand.backend.dto.ApiResponse;
import com.hurryhand.backend.dto.editprofile.*;
import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.dto.user.ProfilePhotoResponseDTO;
import com.hurryhand.backend.dto.user.UserResponseDTO;
import com.hurryhand.backend.exceptions.user.UserNotFoundException;
import com.hurryhand.backend.mappers.ApiResponseMapper;
import com.hurryhand.backend.mappers.UserMapper;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.security.CustomUserDetails;
import com.hurryhand.backend.services.MinioService;
import com.hurryhand.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class UserControllerREST {

    private final UserService userService;
    private final UserMapper userMapper;
    private final ApiResponseMapper apiResponseMapper;
    private final MinioService minioService;

    @PostMapping()
    @AddNewUserDoc
    public ResponseEntity<ApiResponse> addNewUser(@Valid @RequestBody CreateUserDTO newUserDTO) {

        userService.addNewUser(newUserDTO);

        return apiResponseMapper.makeResponseEntity(HttpStatus.OK,"Usuario registrado exitosamente");
    }

    @GetMapping("/id/{id}")
    @GetUserByIdDoc
    public ResponseEntity<UserResponseDTO> getUserByIdForResponse(@PathVariable Long id) {

        UserResponseDTO userDTO = userService.getUserByIdForResponse(id);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmailForResponse(@PathVariable String email) {

        UserResponseDTO userDTO = userService.getUserByEmailForResponse(email);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsersForResponse(), HttpStatus.OK);
    }

    // Parte de editar user
    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/phone")
    public ResponseEntity<ApiResponse> changePhoneNumber(@RequestBody @Valid ChangePhoneRequestDTO request,
                                                       @AuthenticationPrincipal CustomUserDetails userDetails) throws UserNotFoundException{
        User user = userService.getUserById(userDetails.getId());
        userService.changePhoneNumber(user, request);
        return apiResponseMapper.makeResponseEntity(HttpStatus.OK,"Telefono actualizada con exito");
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/email")
    public ResponseEntity<AuthResponse> changeEmail(@RequestBody @Valid ChangeEmailRequestDTO request,
                                                    @AuthenticationPrincipal CustomUserDetails userDetails) throws UserNotFoundException{
        User user = userService.getUserById(userDetails.getId());
        return ResponseEntity.ok(userService.changeEmail(user, request));
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/name")
    public ResponseEntity<ApiResponse> changeNameAndSurname(@RequestBody @Valid ChangeNameRequestDTO request,
                                                       @AuthenticationPrincipal CustomUserDetails userDetails) throws UserNotFoundException{
        User user = userService.getUserById(userDetails.getId());
        userService.changeName(user, request);
        return apiResponseMapper.makeResponseEntity(HttpStatus.OK, "Nombre actualizada con exito.");
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/surname")
    public ResponseEntity<ApiResponse> changeSurname(@RequestBody @Valid ChangeSurnameRequestDTO request,
                                                            @AuthenticationPrincipal CustomUserDetails userDetails) throws UserNotFoundException {
        User user = userService.getUserById(userDetails.getId());
        userService.changeSurname(user, request);
        return apiResponseMapper.makeResponseEntity(HttpStatus.OK, "Nombre actualizada con exito.");
    }


    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/password")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody @Valid ChangePasswordRequestDTO request,
                                                                   @AuthenticationPrincipal CustomUserDetails userDetails) throws UserNotFoundException{
        User user = userService.getUserById(userDetails.getId());
        userService.changePassword(user, request);

        return apiResponseMapper.makeResponseEntity(HttpStatus.OK, "Contrasena actualizada con exito.");
    }


    @PostMapping("/profile-photo")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> uploadPhoto(@RequestParam MultipartFile file,
                              @AuthenticationPrincipal CustomUserDetails userDetails) throws Exception {

        User user = userService.getUserById(userDetails.getId());

        return ResponseEntity.ok(minioService.uploadUserProfilePhoto(user, file));

    }





}

