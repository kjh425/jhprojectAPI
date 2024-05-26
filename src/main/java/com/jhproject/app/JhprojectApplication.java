package com.jhproject.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JhprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JhprojectApplication.class, args);
	}

}
