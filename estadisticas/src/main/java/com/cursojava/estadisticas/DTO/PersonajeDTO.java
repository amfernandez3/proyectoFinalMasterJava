package com.cursojava.estadisticas.DTO;


import lombok.Data;

@Data
public class PersonajeDTO {
    private String nombrePersonaje;

    //Atributos de batalla
    private boolean alineacionAliado = false;
    private int ataquePersonaje;
    private int defensaPersonaje;
    private int vitalidadPersonaje;

    //Atributos de gestion del personaje
    private int experienciaActual;
    private int experienciaSiguienteNivel;
    private int nivel;
}