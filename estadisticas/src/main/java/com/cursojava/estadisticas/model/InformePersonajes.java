package com.cursojava.estadisticas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class InformePersonajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInforme;
    private int numeroPersonajes;
    private int nivelMedioPersonajes;
    private String personajeMasVida;
    private String personajeMasAtaque;
    private String personajeMasDefensa;

}
