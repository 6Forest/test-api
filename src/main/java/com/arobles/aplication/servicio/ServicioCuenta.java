
package com.arobles.aplication.servicio;


import com.arobles.aplication.modelo.Cuenta;
import com.arobles.aplication.repositorio.RepositorioCuenta;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ServicioCuenta {

    @Autowired
    RepositorioCuenta repositorioCuenta;

    public Iterable<Cuenta> listar() {
        return repositorioCuenta.findAll();
    }

    public Cuenta guardar(Cuenta cuenta) {
        return repositorioCuenta.save(cuenta);
    }

    public Optional<Cuenta> buscar(Long numeroCuenta) {
        return repositorioCuenta.findById(numeroCuenta);
    }

    public void eliminar(Long numeroCuenta) {
        repositorioCuenta.deleteById(numeroCuenta);
    }
}