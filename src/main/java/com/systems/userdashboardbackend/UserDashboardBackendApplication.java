package com.systems.userdashboardbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootApplication
public class UserDashboardBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserDashboardBackendApplication.class, args);

	}


}
