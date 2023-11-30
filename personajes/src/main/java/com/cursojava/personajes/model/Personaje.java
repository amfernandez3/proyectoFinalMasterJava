package com.cursojava.personajes.model;

import jakarta.persistence.*;

import java.util.Random;

@Entity
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

    //*********************************************************** Getters & Setters
    public String getNombrePersonaje() {
        return nombrePersonaje;
    }

    public void setNombrePersonaje(String nombrePersonaje) {
        this.nombrePersonaje = nombrePersonaje;
    }

    public boolean isAlineacionAliado() {
        return alineacionAliado;
    }

    public void setAlineacionAliado(boolean alineacionAliado) {
        this.alineacionAliado = alineacionAliado;
    }

    public int getAtaquePersonaje() {
        return ataquePersonaje;
    }

    public void setAtaquePersonaje(int ataquePersonaje) {
        this.ataquePersonaje = ataquePersonaje;
    }

    public int getDefensaPersonaje() {
        return defensaPersonaje;
    }

    public void setDefensaPersonaje(int defensaPersonaje) {
        this.defensaPersonaje = defensaPersonaje;
    }

    public int getVitalidadPersonaje() {
        return vitalidadPersonaje;
    }

    public void setVitalidadPersonaje(int vitalidadPersonaje) {
        this.vitalidadPersonaje = vitalidadPersonaje;
    }

    public int getExperienciaActual() {
        return experienciaActual;
    }

    public void setExperienciaActual(int experienciaActual) {
        this.experienciaActual = experienciaActual;
    }

    public int getExperienciaSiguienteNivel() {
        return experienciaSiguienteNivel;
    }

    public void setExperienciaSiguienteNivel(int experienciaSiguienteNivel) {
        this.experienciaSiguienteNivel = experienciaSiguienteNivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
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
