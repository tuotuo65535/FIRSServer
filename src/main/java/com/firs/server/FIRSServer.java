package com.firs.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class FIRSServer extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FIRSServer.class, args);
	}
	
}
