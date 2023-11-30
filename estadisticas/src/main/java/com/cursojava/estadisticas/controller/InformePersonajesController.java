package com.cursojava.estadisticas.controller;

import com.cursojava.estadisticas.model.InformePersonajes;
import com.cursojava.estadisticas.service.InformePersonajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/informePersonajes")
public class InformePersonajesController {

    @Autowired
    private InformePersonajesService informePersonajesService;

    @GetMapping
    public ResponseEntity<InformePersonajes> obtenerInforme() {
        InformePersonajes informePersonajes = new InformePersonajes();
        if (informePersonajes != null) {

            informePersonajes.setNumeroPersonajes(informePersonajesService.pedirNumeroPersonajes());
            informePersonajes.setNivelMedioPersonajes(informePersonajesService.pedirNivelMedio());
            informePersonajes.setPersonajeMasVida(informePersonajesService.pedirmasvida());

            return new ResponseEntity<>(informePersonajes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
