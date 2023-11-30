package com.cursojava.personajes.serviceImp;

import com.cursojava.personajes.DAO.PersonajeDAO;
import com.cursojava.personajes.model.Personaje;
import com.cursojava.personajes.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PersonajeServiceImp implements PersonajeService {

    @Autowired
    private PersonajeDAO personajeDAO;

    @Override
    public Personaje guardarPersonaje(Personaje personaje) {
        return personajeDAO.save(personaje);
    }

    @Override
    public List<Personaje> obtenerTodosLosPersonajes() {
        return personajeDAO.findAll();
    }

    @Override
    public Personaje obtenerPersonajePorId(int id) {
        return personajeDAO.findById(id).orElse(null);
    }

    @Override
    public void eliminarPersonaje(int id) {
        personajeDAO.deleteById(id);
    }

    // ***********************************************Funciones de informes
    @Override
    public int obtenerNumeroPersonajes() {
        try {
            // Utiliza el repositorio (PersonajeDAO) para obtener el número de personajes
            return personajeDAO.obtenerNumeroPersonajes();
        } catch (Exception e) {
            // Manejar errores y, opcionalmente, lanzar excepciones específicas si es necesario
            System.err.println("Error al obtener el número de personajes: " + e.getMessage());
            throw new RuntimeException("Error al obtener el número de personajes", e);
        }
    }

    @Override
    public int obtenerNivelMedio() {
        try {
            // Utiliza el repositorio (PersonajeDAO) para obtener el nivel medio
            return personajeDAO.obtenerNivelMedio();
        } catch (Exception e) {
            // Manejar errores y, opcionalmente, lanzar excepciones específicas si es necesario
            System.err.println("Error al obtener el nivel medio: " + e.getMessage());
            throw new RuntimeException("Error al obtener el nivel medio", e);
        }
    }

    @Override
    public String pedirPersonajeMasVida() {
        // Lógica para obtener el nombre del personaje con más vida
        List<Personaje> personajes = personajeDAO.findAll();

        if (personajes.isEmpty()) {
            return null;  // No hay personajes en la base de datos
        }

        // Encontrar el personaje con más vida
        Personaje personajeMasVida = Collections.max(personajes, Comparator.comparingInt(Personaje::getVitalidadPersonaje));

        return personajeMasVida.getNombrePersonaje();
    }
}
