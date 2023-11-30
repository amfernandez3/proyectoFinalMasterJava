package com.cursojava.personajes.service;

import com.cursojava.personajes.model.Personaje;

import java.util.List;

public interface PersonajeService {

    //Funciones principales implementadas:

    //Guardar un personaje en base de datos
    Personaje guardarPersonaje(Personaje personaje);

    //Obtener todos los personajes de la base de datos
    List<Personaje> obtenerTodosLosPersonajes();

    //Obtener un personaje por id
    Personaje obtenerPersonajePorId(int id);

    //Eliminar un personaje de la base de datos por id
    void eliminarPersonaje(int id);

    int obtenerNumeroPersonajes();
    int obtenerNivelMedio();

    String pedirPersonajeMasVida();
}
