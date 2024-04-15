package com.example.springminiproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
	info = @Info(
		title = "SR Group 2 Mini Project Spring",
		version = "v1",
		description = "Mini Project Spring"
	)
)
//@SecurityScheme(
//        name = "bearerAuth",
//        type = SecuritySchemeType.HTTP,
//        scheme = "bearer",
//        in = SecuritySchemeIn.HEADER
//)
@SpringBootApplication
public class SpringMiniProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMiniProjectApplication.class, args);
	}

}
