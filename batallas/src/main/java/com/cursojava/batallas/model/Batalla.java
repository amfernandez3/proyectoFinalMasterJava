package com.cursojava.batallas.model;

import com.cursojava.batallas.DTO.PersonajeDTO;
import jakarta.persistence.*;
import java.util.Random;

@Entity
@Table(name = "Batallas")
public class Batalla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBatalla;
    private String ubicacionBatalla;
    private int personajeId1;
    private int personajeId2;
    boolean victoriaPj1 = false;

    public Batalla() {
        asignarUbicacion();
        this.personajeId1 = asignarValorAleatorio(0,10);
        this.personajeId2 = asignarValorAleatorio(0,10);
    }

    public Batalla(int personajeId1, int personajeId2) {
        asignarUbicacion();
        this.personajeId1 = personajeId1;
        this.personajeId2 = personajeId2;
    }

    public void asignarUbicacion(){
        String[] ubicaciones = {"Bosque", "Montaña", "Tundra", "Cueva"};
        Random random = new Random();
        this.ubicacionBatalla = ubicaciones[random.nextInt(ubicaciones.length)];
    }

    @Override
    public String toString() {
        return "Batalla{" +
                "idBatalla=" + idBatalla +
                ", ubicacionBatalla='" + ubicacionBatalla + '\'' +
                ", personajeId1='" + personajeId1 + '\'' +
                ", personajeId2='" + personajeId2 + '\'' +
                ", victoriaPj1=" + victoriaPj1 +
                '}';
    }

    public static int asignarValorAleatorio(int numeroMinimo, int numeroMaximo) {
        // Crea una instancia de la clase Random
        Random random = new Random();

        // Genera un número aleatorio entre 2 y 5 (ambos inclusive)
        int randomNumber = random.nextInt(numeroMaximo) + numeroMinimo;

        return randomNumber;
    }

    public int getIdBatalla() {
        return idBatalla;
    }

    public String getUbicacionBatalla() {
        return ubicacionBatalla;
    }

    public void setUbicacionBatalla(String ubicacionBatalla) {
        this.ubicacionBatalla = ubicacionBatalla;
    }

    public int getPersonajeId1() {
        return personajeId1;
    }

    public void setPersonajeId1(int personajeId1) {
        this.personajeId1 = personajeId1;
    }

    public int getPersonajeId2() {
        return personajeId2;
    }

    public void setPersonajeId2(int personajeId2) {
        this.personajeId2 = personajeId2;
    }

    public boolean isVictoriaPj1() {
        return victoriaPj1;
    }

    public void setVictoriaPj1(boolean victoriaPj1) {
        this.victoriaPj1 = victoriaPj1;
    }

    //Métodos de batalla

    public String gestionBatalla(PersonajeDTO personajeDTO1, PersonajeDTO personajeDTO2){
        String mensaje = "";
        int turnosBatalla = 0;
        while(personajeDTO1.getVitalidadPersonaje()>0 && personajeDTO2.getVitalidadPersonaje() >0){
            if(turnosBatalla%2 == 0){
                personajeDTO2.setVitalidadPersonaje(personajeDTO2.getVitalidadPersonaje()-calcularDaño(personajeDTO1,personajeDTO2));
            }
            else{
                personajeDTO1.setVitalidadPersonaje(personajeDTO1.getVitalidadPersonaje()-calcularDaño(personajeDTO2,personajeDTO1));
            }
            turnosBatalla++;
        }
        if(personajeDTO1.getVitalidadPersonaje()>0){
            this.victoriaPj1= true;
            mensaje += ("El ganador es : " + personajeDTO1.getNombrePersonaje());
        }
        else{
           mensaje += ("El ganador es: " + personajeDTO2.getNombrePersonaje());
        }

        aumentarExperiencia(personajeDTO1,personajeDTO2);
        System.out.println("experiencia de: " + personajeDTO1.getNombrePersonaje() + " es " + personajeDTO1.getExperienciaActual());
        System.out.println("experiencia de: " + personajeDTO2.getNombrePersonaje() + " es " + personajeDTO2.getExperienciaActual());

        return mensaje;
    }

    public int calcularDaño(PersonajeDTO atacante, PersonajeDTO defensor){
        Random random = new Random();

        int danhoAtacante = atacante.getAtaquePersonaje() + random.nextInt(5);
        int proteccionDefensor = defensor.getDefensaPersonaje() + random.nextInt(2);

        //Calculo de daños
        int resultado = (danhoAtacante - proteccionDefensor) > 0 ? danhoAtacante - proteccionDefensor : 0;
        System.out.println("El personaje " + atacante.getNombrePersonaje() + " ha hecho " + resultado + " puntos de daño.");
        return resultado;
    }

    //******************************************* Funciones experiencia + nivel

    public void aumentarExperiencia(PersonajeDTO personajeDTO1, PersonajeDTO personajeDTO2){
        if(this.victoriaPj1){
            personajeDTO1.setExperienciaActual(personajeDTO1.getExperienciaActual() + (15 * personajeDTO2.getNivel())+1);
            System.out.println("El personaje : " + personajeDTO1.getNombrePersonaje()+ " ha ganado " + 15 * (personajeDTO2.getNivel()+1) +"px.");
            comprobarsubidaNivel(personajeDTO1);
        }
        else{
            personajeDTO2.setExperienciaActual(personajeDTO2.getExperienciaActual()+ (15 * personajeDTO1.getNivel())+1);
            System.out.println("El personaje : " + personajeDTO2.getNombrePersonaje()+ " ha ganado " + 15 * (personajeDTO1.getNivel()+1) +"px.");
            comprobarsubidaNivel(personajeDTO2);
        }
    }

    public void comprobarsubidaNivel(PersonajeDTO personajeDTO){
        if(personajeDTO.getExperienciaActual()>= personajeDTO.getExperienciaSiguienteNivel()){
            personajeDTO.setNivel(personajeDTO.getNivel()+1);
            personajeDTO.setExperienciaSiguienteNivel(personajeDTO.getExperienciaSiguienteNivel()+ 10* personajeDTO.getNivel());
            personajeDTO.setExperienciaActual(0);
            System.out.println("Ha subido de nivel!");
        }
    }
}
