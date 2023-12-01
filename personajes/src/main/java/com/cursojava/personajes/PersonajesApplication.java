package com.cursojava.personajes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PersonajesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonajesApplication.class, args);
	}

	// Configuración de RestTemplate
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
