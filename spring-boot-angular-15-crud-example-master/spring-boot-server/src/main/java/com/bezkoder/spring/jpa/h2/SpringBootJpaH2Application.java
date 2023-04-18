package com.bezkoder.spring.jpa.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.bezkoder.spring.jpa.h2.controller","com.bezkoder.spring.jpa.h2.service","com.bezkoder.spring.jpa.h2.mapper"})
@EntityScan("com.bezkoder.spring.jpa.h2.Entity")
@EnableJpaRepositories("com.bezkoder.spring.jpa.h2.repository")
public class SpringBootJpaH2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaH2Application.class, args);
	}





}