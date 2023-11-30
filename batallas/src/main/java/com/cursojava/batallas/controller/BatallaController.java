package com.cursojava.batallas.controller;

import com.cursojava.batallas.DTO.PersonajeDTO;
import com.cursojava.batallas.model.Batalla;
import com.cursojava.batallas.service.BatallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/batallas")
public class BatallaController {

    @Autowired
    private BatallaService batallaService;

    //********************************************* METODOS CRUD *********************
    @PostMapping
    public ResponseEntity<Batalla> guardarBatalla(@RequestBody Batalla batalla) {
        Batalla nuevaBatalla = batallaService.guardarBatalla(batalla);
        return new ResponseEntity<>(nuevaBatalla, HttpStatus.CREATED);
    }

    @PostMapping("/varias")
    public ResponseEntity<List<Batalla>> guardarBatallas(@RequestBody List<Batalla> batallas) {
        List<Batalla> nuevasBatallas = batallaService.guardarBatallas(batallas);
        return new ResponseEntity<>(nuevasBatallas, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Batalla>> obtenerTodasLasBatallas() {
        List<Batalla> batallas = batallaService.obtenerTodasLasBatallas();
        return new ResponseEntity<>(batallas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Batalla> obtenerBatallaPorId(@PathVariable int id) {
        Batalla batalla = batallaService.obtenerBatallaPorId(id);
        if (batalla != null) {
            return new ResponseEntity<>(batalla, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBatalla(@PathVariable int id) {
        batallaService.eliminarBatalla(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Batalla> modificarBatalla(@PathVariable int id, @RequestBody Batalla batallaModificada) {
        Batalla batallaExistente = batallaService.obtenerBatallaPorId(id);

        if (batallaExistente != null) {
            // Actualizar los atributos de la batalla existente con los de la batalla modificada
            batallaExistente.setUbicacionBatalla(batallaModificada.getUbicacionBatalla());
            Batalla batallaActualizada = batallaService.guardarBatalla(batallaExistente);
            return new ResponseEntity<>(batallaActualizada, HttpStatus.OK);
        } else {
            // Devolver NOT FOUND si la batalla no existe
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //****************************************************** METODOS ESPECIFICOS

    @PostMapping("/realizarBatalla/{idBatalla}")
    public ResponseEntity<String> realizarBatalla(@PathVariable int idBatalla) {
        String resultadoBatalla = batallaService.realizarBatalla(idBatalla);

        if (resultadoBatalla.equals("Batalla realizada")) {
            return new ResponseEntity<>("Batalla realizada con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultadoBatalla, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método que comprueba si los personajes se han cargado de manera efectiva
     * @param id
     * @return
     */
    @GetMapping("/{id}/batallainfo")
    public ResponseEntity<Map<String, Object>> getPersonajesDeBatalla(@PathVariable int id) {
        Batalla batalla = batallaService.obtenerBatallaPorId(id);

        if (batalla != null) {
            // Cargar los PersonajeDTO utilizando el servicio de batallas
            PersonajeDTO personajeDTO1 = batallaService.cargarPersonaje(batalla.getPersonajeId1());
            PersonajeDTO personajeDTO2 = batallaService.cargarPersonaje(batalla.getPersonajeId2());

            // Crear un mapa para almacenar la información de la respuesta
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("ubicacionBatalla", batalla.getUbicacionBatalla());
            respuesta.put("personajeDTO1", personajeDTO1);
            respuesta.put("personajeDTO2", personajeDTO2);

            // Devolver el mapa en la respuesta
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            // Devolver NOT FOUND si la batalla no existe
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
