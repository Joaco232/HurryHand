package com.hurryhand.backend;

import com.hurryhand.backend.util.BcryptGen;
import com.hurryhand.backend.config.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan("com.hurryhand.backend.config")
@EnableConfigurationProperties(MinioProperties.class)
public class HurryhandBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HurryhandBackendApplication.class, args);
		BcryptGen.encriptar("hola123");
	}

}
