package com.vlabs.lmsuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LmsUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsUserApplication.class, args);
	}

}
