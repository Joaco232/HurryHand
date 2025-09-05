package com.hurryhand.backend.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PruebaCloud {

    @GetMapping("/test")
    public String hurryhand() {
        // Va a buscar el index.html que ya está en resources/static
        return "redirect:/pruebaCloud.html";
    }
}