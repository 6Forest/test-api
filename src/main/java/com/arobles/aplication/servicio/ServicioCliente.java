
package com.arobles.aplication.servicio;

import com.arobles.aplication.modelo.Cliente;
import com.arobles.aplication.repositorio.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioCliente {

    @Autowired
    private RepositorioCliente repositorioCliente;

    public Iterable<Cliente> listar() {
        return repositorioCliente.findAll();
    }

    public Cliente guardar(Cliente cliente) {
        return repositorioCliente.save(cliente);
    }

    public Optional<Cliente> buscar(Integer id) {
        return repositorioCliente.findById(id);
    }

    public void eliminar(Integer id) {
        repositorioCliente.deleteById(id);
    }
}