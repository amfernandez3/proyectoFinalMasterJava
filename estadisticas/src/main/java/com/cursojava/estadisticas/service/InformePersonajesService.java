package com.cursojava.estadisticas.service;

import com.cursojava.estadisticas.DTO.PersonajeDTO;
import org.springframework.stereotype.Service;


public interface InformePersonajesService {

    public int pedirNumeroPersonajes();
    int pedirNivelMedio();

    String pedirmasvida();
}
