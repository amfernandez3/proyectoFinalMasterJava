package com.cursojava.estadisticas.serviceImp;

import com.cursojava.estadisticas.DAO.InformePersonajesDAO;
import com.cursojava.estadisticas.service.InformePersonajesService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InformePersonajesServiceImp implements InformePersonajesService {
    InformePersonajesDAO informePersonajesDAO;

    private RestTemplate restTemplate;
    private String personajesUrl ="http://localhost:8080";


    public InformePersonajesServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public int pedirNumeroPersonajes() {
        try {
            // Hacer una solicitud al microservicio de personajes para obtener el número de personajes
            String url = personajesUrl + "/personajes/numPersonajes";
            return restTemplate.getForObject(url, Integer.class);
        } catch (Exception e) {
            System.err.println("Error al obtener el número de personajes: " + e.getMessage());
            throw new RuntimeException("Error al obtener el número de personajes", e);
        }
    }

    @Override
    public int pedirNivelMedio() {
        try {
            // Hacer una solicitud al microservicio de personajes para obtener el nivel medio
            String url = personajesUrl + "/personajes/nivelMedio";
            return restTemplate.getForObject(url, Integer.class);
        } catch (Exception e) {
            System.err.println("Error al obtener el nivel medio: " + e.getMessage());
            throw new RuntimeException("Error al obtener el nivel medio", e);
        }
    }

    @Override
    public String pedirMasVida() {
        try {
            // Hacer una solicitud al microservicio de personajes para obtener la vida más alta
            String url = personajesUrl + "/personajes/masVida";
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            System.err.println("Error al obtener el personaje: " + e.getMessage());
            throw new RuntimeException("Error al obtener el personaje", e);
        }
    }

    @Override
    public String pedirMasAtaque() {
        try {
            // Hacer una solicitud al microservicio de personajes para obtener el ataque más alto
            String url = personajesUrl + "/personajes/masAtaque";
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            System.err.println("Error al obtener el personaje: " + e.getMessage());
            throw new RuntimeException("Error al obtener el personaje", e);
        }
    }

    @Override
    public String pedirMasDefensa() {
        try {
            // Hacer una solicitud al microservicio de personajes para obtener la defensa más alta
            String url = personajesUrl + "/personajes/masDefensa";
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            System.err.println("Error al obtener el personaje: " + e.getMessage());
            throw new RuntimeException("Error al obtener el personaje", e);
        }
    }


}
