package com.cursojava.personajes.DAO;

import com.cursojava.personajes.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonajeDAO extends JpaRepository<Personaje,Integer> {


    //Métodos añadidos para consultas:
    List<Personaje> findByNombrePersonaje(String nombre);

}
