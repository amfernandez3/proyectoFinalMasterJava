package com.cursojava.batallas.DAO;

import com.cursojava.batallas.model.Batalla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatallaDAO extends JpaRepository<Batalla,Integer> {


    //Métodos añadidos para consultas:
    List<Batalla> findByUbicacionBatalla(String nombre);

}