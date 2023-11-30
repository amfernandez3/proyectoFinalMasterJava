package com.cursojava.personajes.controller;

import com.cursojava.personajes.model.Personaje;
import com.cursojava.personajes.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("personajes")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    /**
     * Permite guardar un personaje en la base de datos
     * @param personaje recibe el personaje a introducir en la db
     * @return devuelve una respuesta compuesta por el nuevo personaje y el http status
     */
    @PostMapping
    public ResponseEntity<Personaje> guardarPersonaje(@RequestBody Personaje personaje) {
        Personaje nuevoPersonaje = personajeService.guardarPersonaje(personaje);
        return new ResponseEntity<>(nuevoPersonaje, HttpStatus.CREATED);
    }

    @PostMapping("/varios")
    public ResponseEntity<List<Personaje>> guardarPersonajes(@RequestBody List<Personaje> personajes) {
        List<Personaje> nuevosPersonajes = new ArrayList<>();

        for (Personaje personaje : personajes) {
            Personaje nuevoPersonaje = personajeService.guardarPersonaje(personaje);
            nuevosPersonajes.add(nuevoPersonaje);
        }

        return new ResponseEntity<>(nuevosPersonajes, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Personaje>> obtenerTodosLosPersonajes() {
        List<Personaje> personajes = personajeService.obtenerTodosLosPersonajes();
        return new ResponseEntity<>(personajes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaje> obtenerPersonajePorId(@PathVariable int id) {
        Personaje personaje = personajeService.obtenerPersonajePorId(id);
        if (personaje != null) {
            return new ResponseEntity<>(personaje, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersonaje(@PathVariable int id) {
        personajeService.eliminarPersonaje(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personaje> actualizarPersonaje(@PathVariable int id, @RequestBody Personaje personajeActualizado) {
        Personaje personajeExistente = personajeService.obtenerPersonajePorId(id);

        if (personajeExistente != null) {
            personajeExistente.setExperienciaActual(personajeActualizado.getExperienciaActual());
            personajeExistente.setExperienciaSiguienteNivel(personajeActualizado.getExperienciaSiguienteNivel());
            personajeExistente.setNivel(personajeActualizado.getNivel());

            Personaje personajeActualizadoDB = personajeService.guardarPersonaje(personajeExistente);
            return new ResponseEntity<>(personajeActualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
