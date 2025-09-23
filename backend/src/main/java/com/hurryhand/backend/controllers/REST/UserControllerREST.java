package com.hurryhand.backend.controllers.REST;


import com.hurryhand.backend.auth.AuthResponse;
import com.hurryhand.backend.decorators.AddNewUserDoc;
import com.hurryhand.backend.decorators.GetUserByIdDoc;
import com.hurryhand.backend.dto.editprofie.ChangeEmailRequestDTO;
import com.hurryhand.backend.dto.editprofie.ChangeNameRequestDTO;
import com.hurryhand.backend.dto.editprofie.ChangePasswordRequestDTO;
import com.hurryhand.backend.dto.editprofie.ChangePhoneRequestDTO;
import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.dto.user.UserResponseDTO;
import com.hurryhand.backend.exceptions.user.UserNotFoundException;
import com.hurryhand.backend.mappers.UserMapper;
import com.hurryhand.backend.models.User;
import com.hurryhand.backend.security.CustomUserDetails;
import com.hurryhand.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class UserControllerREST {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping()
    @AddNewUserDoc
    public ResponseEntity<Map<String, String>> addNewUser(@Valid @RequestBody CreateUserDTO newUserDTO) {

        userService.addNewUser(newUserDTO);

        Map<String, String> message = new HashMap<>();
        message.put("mensaje", "Usuario registrado exitosamente");

        return new ResponseEntity<>(message, HttpStatus.OK);
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
    public ResponseEntity<UserResponseDTO> changePhone(@RequestBody @Valid ChangePhoneRequestDTO request,
                                                       @AuthenticationPrincipal CustomUserDetails userDetails) throws UserNotFoundException{
        User user = userService.getUserById(userDetails.getId());
        return ResponseEntity.ok(userService.changePhone(user, request));
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
    public ResponseEntity<UserResponseDTO> changeNameAndSurname(@RequestBody @Valid ChangeNameRequestDTO request,
                                                       @AuthenticationPrincipal CustomUserDetails userDetails) throws UserNotFoundException{
        User user = userService.getUserById(userDetails.getId());
        return ResponseEntity.ok(userService.changeNameAndSurname(user, request));
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/password")
    public ResponseEntity<UserResponseDTO> changePassword(@RequestBody @Valid ChangePasswordRequestDTO request,
                                                                   @AuthenticationPrincipal CustomUserDetails userDetails) throws UserNotFoundException{
        User user = userService.getUserById(userDetails.getId());
        return ResponseEntity.ok(userService.changePassword(user, request));
    }


}

