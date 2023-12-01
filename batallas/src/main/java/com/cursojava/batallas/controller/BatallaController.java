package com.cursojava.batallas.controller;

import com.cursojava.batallas.DTO.PersonajeDTO;
import com.cursojava.batallas.model.Batalla;
import com.cursojava.batallas.service.BatallaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/batallas")
@Api(tags = "Batallas", description = "Operaciones relacionadas con las batallas")
public class BatallaController {

    @Autowired
    private BatallaService batallaService;

    // ********************************************* METODOS CRUD *********************
    @ApiOperation(value = "Guardar una nueva batalla",
            notes = "Permite guardar una batalla en la base de datos.")
    @PostMapping
    public ResponseEntity<Batalla> guardarBatalla(@RequestBody Batalla batalla) {
        Batalla nuevaBatalla = batallaService.guardarBatalla(batalla);
        return new ResponseEntity<>(nuevaBatalla, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Guardar varias batallas",
            notes = "Permite guardar varias batallas en la base de datos.")
    @PostMapping("/varias")
    public ResponseEntity<List<Batalla>> guardarBatallas(@RequestBody List<Batalla> batallas) {
        List<Batalla> nuevasBatallas = batallaService.guardarBatallas(batallas);
        return new ResponseEntity<>(nuevasBatallas, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Obtener todas las batallas",
            notes = "Permite obtener todas las batallas almacenadas en la base de datos.")
    @GetMapping
    public ResponseEntity<List<Batalla>> obtenerTodasLasBatallas() {
        List<Batalla> batallas = batallaService.obtenerTodasLasBatallas();
        return new ResponseEntity<>(batallas, HttpStatus.OK);
    }

    @ApiOperation(value = "Obtener una batalla por ID",
            notes = "Permite obtener una batalla específica por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Batalla> obtenerBatallaPorId(
            @ApiParam(value = "ID de la batalla", required = true)
            @PathVariable int id) {
        Batalla batalla = batallaService.obtenerBatallaPorId(id);
        if (batalla != null) {
            return new ResponseEntity<>(batalla, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Eliminar una batalla por ID",
            notes = "Permite eliminar una batalla específica por su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBatalla(
            @ApiParam(value = "ID de la batalla a eliminar", required = true)
            @PathVariable int id) {
        batallaService.eliminarBatalla(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Modificar una batalla por ID",
            notes = "Permite modificar una batalla específica por su ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Batalla> modificarBatalla(
            @ApiParam(value = "ID de la batalla a modificar", required = true)
            @PathVariable int id,
            @RequestBody Batalla batallaModificada) {
        Batalla batallaExistente = batallaService.obtenerBatallaPorId(id);

        if (batallaExistente != null) {
            batallaExistente.setUbicacionBatalla(batallaModificada.getUbicacionBatalla());
            Batalla batallaActualizada = batallaService.guardarBatalla(batallaExistente);
            return new ResponseEntity<>(batallaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ****************************************************** METODOS ESPECIFICOS

    @ApiOperation(value = "Realizar una batalla por ID",
            notes = "Permite realizar una batalla específica por su ID.")
    @PostMapping("/realizarBatalla/{idBatalla}")
    public ResponseEntity<String> realizarBatalla(
            @ApiParam(value = "ID de la batalla a realizar", required = true)
            @PathVariable int idBatalla) {
        String resultadoBatalla = batallaService.realizarBatalla(idBatalla);

        if (resultadoBatalla.equals("Batalla realizada")) {
            return new ResponseEntity<>("Batalla realizada con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultadoBatalla, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Obtener información de los personajes de una batalla por ID",
            notes = "Permite obtener información detallada de los personajes involucrados en una batalla específica por su ID.")
    @GetMapping("/{id}/batallainfo")
    public ResponseEntity<Map<String, Object>> getPersonajesDeBatalla(
            @ApiParam(value = "ID de la batalla", required = true)
            @PathVariable int id) {
        Batalla batalla = batallaService.obtenerBatallaPorId(id);

        if (batalla != null) {
            PersonajeDTO personajeDTO1 = batallaService.cargarPersonaje(batalla.getPersonajeId1());
            PersonajeDTO personajeDTO2 = batallaService.cargarPersonaje(batalla.getPersonajeId2());

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("ubicacionBatalla", batalla.getUbicacionBatalla());
            respuesta.put("personajeDTO1", personajeDTO1);
            respuesta.put("personajeDTO2", personajeDTO2);

            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
