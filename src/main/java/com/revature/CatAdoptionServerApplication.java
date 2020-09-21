package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
public class CatAdoptionServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatAdoptionServerApplication.class, args);
	}

}
