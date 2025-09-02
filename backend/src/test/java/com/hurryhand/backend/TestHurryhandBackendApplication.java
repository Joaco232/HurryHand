package com.hurryhand.backend;

import org.springframework.boot.SpringApplication;

public class TestHurryhandBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(HurryhandBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
