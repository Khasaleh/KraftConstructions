package com.bezkoder.spring.jpa.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan({"com.bezkoder.spring.jpa.h2.controller","com.bezkoder.spring.jpa.h2.service","com.bezkoder.spring.jpa.h2.mapper"})
@EntityScan("com.bezkoder.spring.jpa.h2.Entity")
@EnableJpaRepositories("com.bezkoder.spring.jpa.h2.repository")
public class SpringBootJpaH2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaH2Application.class, args);
	}





}
