package com.cursojava.batallas.DTO;

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

    public PersonajeDTO() {

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

}
