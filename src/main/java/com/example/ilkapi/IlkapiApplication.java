package com.example.ilkapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@ComponentScan(basePackages = {"com.example.ilkapi"})
@EnableJpaRepositories(basePackages = {"com.example.ilkapi.repository"})
@EntityScan(basePackages = {"com.example.ilkapi.entity"})
@SpringBootApplication
public class IlkapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IlkapiApplication.class, args);
	}

}
