package com.cursojava.batallas.service;

import com.cursojava.batallas.DTO.PersonajeDTO;
import com.cursojava.batallas.model.Batalla;

import java.util.List;

public interface BatallaService {
    Batalla guardarBatalla(Batalla batalla);
    List<Batalla> obtenerTodasLasBatallas();
    Batalla obtenerBatallaPorId(int idBatalla);
    void eliminarBatalla(int idBatalla);
    List<Batalla> guardarBatallas(List<Batalla> batallas);
    // Puedes agregar más métodos según las operaciones que necesites
    //Gestion de Personajes
    PersonajeDTO cargarPersonaje(int id);

    String realizarBatalla(int idBatalla);
    void actualizarPersonajes(Batalla batalla, PersonajeDTO personajeDTO1, PersonajeDTO personajeDTO2);
}
