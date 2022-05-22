package com.demo.usermanager.launcher;

import com.demo.usermanager.domain.config.RegisterUserInputValidationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.usermanager.application.controller",
								"com.demo.usermanager.application.mappers",
								"com.demo.usermanager.domain.services",
								"com.demo.usermanager.infrastructure.adapters",
								"com.demo.usermanager.infrastructure.mappers",
								"com.demo.usermanager.domain.validators",
								"com.demo.usermanager.launcher.config"})
@EntityScan(basePackages = {"com.demo.usermanager.infrastructure.entity"})
@EnableConfigurationProperties(RegisterUserInputValidationProperties.class)
@EnableJpaRepositories(basePackages = {"com.demo.usermanager.infrastructure.repository"})
public class UserManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);
	}

}
