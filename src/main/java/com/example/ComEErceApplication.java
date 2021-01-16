package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@EntityScan(basePackages = {"com.example.model"})
@SpringBootApplication
public class ComEErceApplication {

	public static void main(String[] args) {
			SpringApplication.run(ComEErceApplication.class, args);
	}
	   
}

