package com.hurryhand.backend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptGen {
    public static void encriptar(String pass) {

        var enc = new BCryptPasswordEncoder();
        System.out.println("Hash BCrypt de " + pass + " = " + enc.encode(pass));
    }
}