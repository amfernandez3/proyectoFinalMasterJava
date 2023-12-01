package com.cursojava.personajes.controller;

import com.cursojava.personajes.model.Personaje;
import com.cursojava.personajes.service.PersonajeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("personajes")
@Api(tags = "Personajes", description = "Operaciones relacionadas con los personajes")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @ApiOperation(value = "Guardar un nuevo personaje",
            notes = "Permite guardar un personaje en la base de datos.")
    @PostMapping
    public ResponseEntity<Personaje> guardarPersonaje(@RequestBody Personaje personaje) {
        Personaje nuevoPersonaje = personajeService.guardarPersonaje(personaje);
        return new ResponseEntity<>(nuevoPersonaje, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Guardar varios personajes",
            notes = "Permite guardar varios personajes en la base de datos.")
    @PostMapping("/varios")
    public ResponseEntity<List<Personaje>> guardarPersonajes(@RequestBody List<Personaje> personajes) {
        List<Personaje> nuevosPersonajes = new ArrayList<>();

        for (Personaje personaje : personajes) {
            Personaje nuevoPersonaje = personajeService.guardarPersonaje(personaje);
            nuevosPersonajes.add(nuevoPersonaje);
        }

        return new ResponseEntity<>(nuevosPersonajes, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Obtener todos los personajes",
            notes = "Permite obtener todos los personajes almacenados en la base de datos.")
    @GetMapping
    public ResponseEntity<List<Personaje>> obtenerTodosLosPersonajes() {
        List<Personaje> personajes = personajeService.obtenerTodosLosPersonajes();
        return new ResponseEntity<>(personajes, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtener un personaje por ID",
            notes = "Permite obtener un personaje específico por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Personaje> obtenerPersonajePorId(
            @ApiParam(value = "ID del personaje", required = true)
            @PathVariable int id) {
        Personaje personaje = personajeService.obtenerPersonajePorId(id);
        if (personaje != null) {
            return new ResponseEntity<>(personaje, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Eliminar un personaje por ID",
            notes = "Permite eliminar un personaje específico por su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersonaje(
            @ApiParam(value = "ID del personaje a eliminar", required = true)
            @PathVariable int id) {
        personajeService.eliminarPersonaje(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Actualizar un personaje por ID",
            notes = "Permite actualizar un personaje específico por su ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Personaje> actualizarPersonaje(
            @ApiParam(value = "ID del personaje a actualizar", required = true)
            @PathVariable int id,
            @RequestBody Personaje personajeActualizado) {
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

    // ******************************************* Funciones de informes

    @ApiOperation(value = "Obtener el número de personajes",
            notes = "Permite obtener el número total de personajes almacenados en la base de datos.")
    @GetMapping("/numPersonajes")
    public ResponseEntity<Integer> getNumeroPersonajes() {
        try {
            int numeroPersonajes = personajeService.obtenerNumeroPersonajes();
            return new ResponseEntity<>(numeroPersonajes, HttpStatus.OK);
        } catch (Exception e) {
            // Manejar errores y devolver un código de estado apropiado
            System.err.println("Error al obtener el número de personajes: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtener el nivel medio de los personajes",
            notes = "Permite obtener el nivel medio de los personajes almacenados en la base de datos.")
    @GetMapping("/nivelMedio")
    public ResponseEntity<Integer> getNivelMedio() {
        try {
            int nivelMedio = personajeService.obtenerNivelMedio();
            return new ResponseEntity<>(nivelMedio, HttpStatus.OK);
        } catch (Exception e) {
            // Manejar errores y devolver un código de estado apropiado
            System.err.println("Error al obtener el nivel medio: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Obtener el nombre del personaje con más vida",
            notes = "Permite obtener el nombre del personaje con la mayor cantidad de vida.")
    @GetMapping("/masVida")
    public ResponseEntity<String> obtenerNombrePersonajeConMasVida() {
        String nombrePersonajeMasVida = personajeService.pedirPersonajeMasVida();
        System.out.println("El personaje con más vida es: " + nombrePersonajeMasVida);

        if (nombrePersonajeMasVida != null) {
            return new ResponseEntity<>(nombrePersonajeMasVida, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Obtener el nombre del personaje con más defensa",
            notes = "Permite obtener el nombre del personaje con la mayor cantidad de defensa.")
    @GetMapping("/masDefensa")
    public ResponseEntity<String> obtenerNombrePersonajeConMasDefensa() {
        String nombrePersonajeMasDefensa = personajeService.pedirPersonajeMasDefensa();
        System.out.println("El personaje con más defensa es: " + nombrePersonajeMasDefensa);

        if (nombrePersonajeMasDefensa != null) {
            return new ResponseEntity<>(nombrePersonajeMasDefensa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Obtener el nombre del personaje con más ataque",
            notes = "Permite obtener el nombre del personaje con la mayor cantidad de ataque.")
    @GetMapping("/masAtaque")
    public ResponseEntity<String> obtenerNombrePersonajeConMasAtaque() {
        String nombrePersonajeMasAtaque = personajeService.pedirPersonajeMasAtaque();
        System.out.println("El personaje con más ataque es: " + nombrePersonajeMasAtaque);

        if (nombrePersonajeMasAtaque != null) {
            return new ResponseEntity<>(nombrePersonajeMasAtaque, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
