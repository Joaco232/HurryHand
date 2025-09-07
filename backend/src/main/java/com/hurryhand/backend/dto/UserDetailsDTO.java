package com.hurryhand.backend.dto;

import com.hurryhand.backend.enums.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {

    private Long id;

    private String password;

    private String email;

    private List<Role> roles = new ArrayList<>();

}