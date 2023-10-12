package com.project.SchoolBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project.SchoolBook")
public class SchoolBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolBookApplication.class, args);
	}

}
