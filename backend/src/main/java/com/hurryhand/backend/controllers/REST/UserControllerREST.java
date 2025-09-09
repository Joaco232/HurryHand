package com.hurryhand.backend.controllers.REST;


import com.hurryhand.backend.decorators.GetUserByIdDoc;
import com.hurryhand.backend.dto.user.CreateUserDTO;
import com.hurryhand.backend.dto.user.UserResponseDTO;
import com.hurryhand.backend.mappers.UserMapper;
import com.hurryhand.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserControllerREST {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping()
    public ResponseEntity<Map<String, String>> addNewUser(@Valid @RequestBody CreateUserDTO newUserDTO) {

        userService.addNewUser(newUserDTO);

        Map<String, String> message = new HashMap<>();
        message.put("mensaje", "Usuario registrado exitosamente");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @GetUserByIdDoc
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {

        UserResponseDTO userDTO = userMapper.toUserResponseDTO(userService.getUserById(id));

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }





}
