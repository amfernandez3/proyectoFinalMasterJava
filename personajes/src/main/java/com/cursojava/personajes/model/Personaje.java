package com.cursojava.personajes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Random;

@Entity
@Data
@Table(name = "Personajes")
public class Personaje {
    //Atributos de identificacion de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersonaje;
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

    public Personaje() {
        definirStatsIniciales(this);
    }

    public Personaje(String nombrePersonaje, boolean alineacionAliado) {
        this.nombrePersonaje = nombrePersonaje;
        this.alineacionAliado = alineacionAliado;
        definirStatsIniciales(this);
    }

    /**
     * Método que permite inicializar los stats iniciales de un personaje
     * @param personaje
     */
    public void definirStatsIniciales(Personaje personaje){
        personaje.setAtaquePersonaje(asignarValorAleatorio(2,5));
        personaje.setDefensaPersonaje(asignarValorAleatorio(0,2));
        personaje.setVitalidadPersonaje(asignarValorAleatorio(30,100));
        personaje.setNivel(0);
        personaje.setExperienciaActual(0);
        personaje.setExperienciaSiguienteNivel(100);
    }
    //*********************************************** Otras funciones

    public static int asignarValorAleatorio(int numeroMinimo, int numeroMaximo) {
        // Crea una instancia de la clase Random
        Random random = new Random();

        // Genera un número aleatorio entre 2 y 5 (ambos inclusive)
        int randomNumber = random.nextInt(numeroMaximo) + numeroMinimo;

        return randomNumber;
    }

}
