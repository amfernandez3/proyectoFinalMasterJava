package com.cursojava.batallas.serviceImp;

import com.cursojava.batallas.DAO.BatallaDAO;
import com.cursojava.batallas.DTO.PersonajeDTO;
import com.cursojava.batallas.model.Batalla;
import com.cursojava.batallas.service.BatallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BatallaServiceImp implements BatallaService {

    @Autowired
    private BatallaDAO batallaDAO;
    private final RestTemplate restTemplate;
    private String personajesUrl ="http://localhost:8080";


    public BatallaServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // ***************************************************************** métodos CRUD
    @Override
    public Batalla guardarBatalla(Batalla batalla) {
        System.out.println("Guardando batalla: " + batalla.toString());
        Batalla batallaGuardada = batallaDAO.save(batalla);
        System.out.println("Batalla guardada: " + batallaGuardada.toString());
        return batallaGuardada;
    }

    @Override
    public List<Batalla> obtenerTodasLasBatallas() {
        return batallaDAO.findAll();
    }

    @Override
    public Batalla obtenerBatallaPorId(int idBatalla) {
        return batallaDAO.findById(idBatalla).orElse(null);
    }

    @Override
    public void eliminarBatalla(int idBatalla) {
        batallaDAO.deleteById(idBatalla);
    }

    @Override
    public List<Batalla> guardarBatallas(List<Batalla> batallas) {
        // Puedes utilizar el repositorio para guardar las batallas en la base de datos
        List<Batalla> batallasGuardadas = batallaDAO.saveAll(batallas);
        return batallasGuardadas;
    }



    //Funciones de gestión de personajes:
    @Override
    public PersonajeDTO cargarPersonaje(int id) {
        try {
            String url = personajesUrl + "/personajes/" + id;
            System.out.println("URL de carga del personaje: " + url);

            return restTemplate.getForObject(url, PersonajeDTO.class);
        } catch (Exception e) {
            // Manejo de errores
            System.err.println("Error al cargar el personaje con ID " + id + ": " + e.getMessage());
            return null;
        }
    }




    @Override
    public String realizarBatalla(int idBatalla) {
        Batalla batalla = obtenerBatallaPorId(idBatalla);

        if (batalla != null) {
            PersonajeDTO personajeDTO1 = cargarPersonaje(batalla.getPersonajeId1());
            PersonajeDTO personajeDTO2 = cargarPersonaje(batalla.getPersonajeId2());

            // Lógica de la batalla y experiencia
            String resultadoBatalla = batalla.gestionBatalla(personajeDTO1, personajeDTO2);

            // Actualizar PersonajesDTO en el microservicio de Personajes
            actualizarPersonajes(batalla, personajeDTO1, personajeDTO2);

            return resultadoBatalla;
        } else {
            return "Batalla no encontrada";
        }
    }

    @Override
    public void actualizarPersonajes(Batalla batalla,PersonajeDTO personajeDTO1, PersonajeDTO personajeDTO2) {

        // Actualiza el primer personaje
        restTemplate.put(personajesUrl + "/personajes/" + batalla.getPersonajeId1(), personajeDTO1);

        // Actualiza el segundo personaje
        restTemplate.put(personajesUrl + "/personajes/" + batalla.getPersonajeId2(), personajeDTO2);
    }


}
