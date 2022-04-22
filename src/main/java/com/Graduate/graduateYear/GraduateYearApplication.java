package com.Graduate.graduateYear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class GraduateYearApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraduateYearApplication.class, args);
	}

}
