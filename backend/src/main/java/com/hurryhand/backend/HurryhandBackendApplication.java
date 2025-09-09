package com.hurryhand.backend;

import com.hurryhand.backend.util.BcryptGen;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HurryhandBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HurryhandBackendApplication.class, args);
		BcryptGen.encriptar("hola123");
	}

}
