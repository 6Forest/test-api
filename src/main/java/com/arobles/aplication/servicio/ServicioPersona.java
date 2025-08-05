
package com.arobles.aplication.servicio;

import com.arobles.aplication.modelo.Persona;
import com.arobles.aplication.repositorio.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioPersona {

    @Autowired
    private RepositorioPersona repositorioPersona;

    public Iterable<Persona> listar() {
        return repositorioPersona.findAll();
    }

    public Persona guardar(Persona persona) {
        return repositorioPersona.save(persona);
    }

    public Optional<Persona> buscar(Integer id) {
        return repositorioPersona.findById(id);
    }

    public void eliminar(Integer id) {
        repositorioPersona.deleteById(id);
    }
}