package com.santander.rest.demorest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.santander.rest.*,com.santander.springsoap.gen")

public class DemoRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoRestApplication.class, args);
	}

}

