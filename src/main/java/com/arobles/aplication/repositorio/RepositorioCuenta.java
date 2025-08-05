package com.arobles.aplication.repositorio;

import com.arobles.aplication.modelo.Cliente;
import com.arobles.aplication.modelo.Cuenta;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioCuenta extends CrudRepository<Cuenta, Long> {

    List<Cuenta> findByCliente(Cliente cliente);

}
