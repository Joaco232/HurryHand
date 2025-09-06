package com.hurryhand.backend.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;

@Controller
public class PruebaCloud {

    @GetMapping("/test")
    public ResponseEntity<String> hurryhand() throws IOException {
        var resource = new ClassPathResource("static/pruebaCloud.html");
        String html = Files.readString(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html);
    }
}