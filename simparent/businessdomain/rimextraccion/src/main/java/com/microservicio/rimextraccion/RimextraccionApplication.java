package com.microservicio.rimextraccion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan(value = { "com.commons.utils.models.entities", "com.microservicio.rimextraccion.models.entities" })
public class RimextraccionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RimextraccionApplication.class, args);
	}

}
