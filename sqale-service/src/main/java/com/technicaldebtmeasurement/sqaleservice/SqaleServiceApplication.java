package com.technicaldebtmeasurement.sqaleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SqaleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqaleServiceApplication.class, args);
	}

}
