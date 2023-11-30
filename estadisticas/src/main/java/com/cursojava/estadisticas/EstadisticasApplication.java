package com.cursojava.estadisticas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EstadisticasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstadisticasApplication.class, args);
	}

	//config de RestTemplate
	@Bean
	public RestTemplate template(){
		return new RestTemplate();
	}
}
