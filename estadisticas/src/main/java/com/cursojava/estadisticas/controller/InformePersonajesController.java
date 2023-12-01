package com.cursojava.estadisticas.controller;

import com.cursojava.estadisticas.model.InformePersonajes;
import com.cursojava.estadisticas.service.InformePersonajesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/informePersonajes")
@Api(tags = "Informes de Personajes", description = "Operaciones relacionadas con los informes de personajes")
public class InformePersonajesController {

    @Autowired
    private InformePersonajesService informePersonajesService;

    @ApiOperation(value = "Obtener informe de personajes",
            notes = "Permite obtener un informe que incluye estadísticas sobre los personajes.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Informe obtenido con éxito"),
            @ApiResponse(code = 404, message = "Informe no encontrado")
    })
    @GetMapping
    public ResponseEntity<InformePersonajes> obtenerInforme() {
        InformePersonajes informePersonajes = new InformePersonajes();
        if (informePersonajes != null) {
            informePersonajes.setNumeroPersonajes(informePersonajesService.pedirNumeroPersonajes());
            informePersonajes.setNivelMedioPersonajes(informePersonajesService.pedirNivelMedio());
            informePersonajes.setPersonajeMasVida(informePersonajesService.pedirMasVida());
            informePersonajes.setPersonajeMasAtaque(informePersonajesService.pedirMasAtaque());
            informePersonajes.setPersonajeMasDefensa(informePersonajesService.pedirMasDefensa());

            return new ResponseEntity<>(informePersonajes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
