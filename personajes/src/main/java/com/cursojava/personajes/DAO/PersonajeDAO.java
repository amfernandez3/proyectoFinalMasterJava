package com.cursojava.personajes.DAO;

import com.cursojava.personajes.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonajeDAO extends JpaRepository<Personaje,Integer> {


    //Métodos añadidos para consultas:
    List<Personaje> findByNombrePersonaje(String nombre);

    @Query("SELECT COUNT(p) FROM Personaje p")
    int obtenerNumeroPersonajes();

    @Query("SELECT AVG(p.nivel) FROM Personaje p")
    int obtenerNivelMedio();

}
