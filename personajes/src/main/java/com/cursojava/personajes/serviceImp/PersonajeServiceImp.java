package com.cursojava.personajes.serviceImp;

import com.cursojava.personajes.DAO.PersonajeDAO;
import com.cursojava.personajes.model.Personaje;
import com.cursojava.personajes.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeServiceImp implements PersonajeService {

    @Autowired
    private PersonajeDAO personajeDAO;

    @Override
    public Personaje guardarPersonaje(Personaje personaje) {
        return personajeDAO.save(personaje);
    }

    @Override
    public List<Personaje> obtenerTodosLosPersonajes() {
        return personajeDAO.findAll();
    }

    @Override
    public Personaje obtenerPersonajePorId(int id) {
        return personajeDAO.findById(id).orElse(null);
    }

    @Override
    public void eliminarPersonaje(int id) {
        personajeDAO.deleteById(id);
    }

    // Puedes implementar más métodos según las operaciones que necesites

}
