package com.hurryhand.backend.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginRequest {

    private String email;

    private String password;
}