package com.project.finance;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "bearerAuth", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT")
public class FinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
	}

}
