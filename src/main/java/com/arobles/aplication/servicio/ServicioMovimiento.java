package com.arobles.aplication.servicio;

import com.arobles.aplication.excepcion.SaldoInsuficienteException;
import com.arobles.aplication.modelo.Cuenta;
import com.arobles.aplication.modelo.Movimiento;
import com.arobles.aplication.repositorio.RepositorioCuenta;
import com.arobles.aplication.repositorio.RepositorioMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class ServicioMovimiento {

    @Autowired
    private RepositorioMovimiento repositorioMovimiento;

    @Autowired
    private RepositorioCuenta repositorioCuenta;

    public Iterable<Movimiento> listar() {
        return repositorioMovimiento.findAll();
    }

    public Optional<Movimiento> buscar(Integer id) {
        return repositorioMovimiento.findById(id);
    }

    public void eliminar(Integer id) {
        repositorioMovimiento.deleteById(id);
    }

    /**
     * Guarda un movimiento y actualiza el saldo de la cuenta.
     *
     * @param movimiento Movimiento a registrar. El campo valor puede ser
     * positivo o negativo.
     * @return Movimiento guardado con saldo actualizado.
     * @throws IllegalArgumentException si la cuenta no existe.
     */
    public Movimiento guardar(Movimiento movimiento) {
        // Validar que la cuenta exista
        Long numeroCuenta = movimiento.getCuenta().getNumeroCuenta();
        Optional<Cuenta> cuentaOpt = repositorioCuenta.findById(numeroCuenta);
        if (!cuentaOpt.isPresent()) {
            throw new IllegalArgumentException("Cuenta no encontrada: " + numeroCuenta);
        }

        Cuenta cuenta = cuentaOpt.get();

        // Calcular nuevo saldo
        BigDecimal saldoActual = cuenta.getSaldoInicial() != null ? cuenta.getSaldoInicial() : BigDecimal.ZERO;

        // Sumar todos movimientos previos para obtener saldo actual actualizado (opcional, si saldoInicial no cambia)
        // Pero para simplificar, si guardamos siempre el saldo correcto en cuenta, podemos usar ese saldo
        // En este ejemplo asumo que saldoInicial es saldo actual para simplicidad
        BigDecimal nuevoSaldo = saldoActual.add(movimiento.getValor());

        // Validar saldo negativo
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException("Saldo no disponible");
        }

        // Actualizar saldo en cuenta
        cuenta.setSaldoInicial(nuevoSaldo);
        repositorioCuenta.save(cuenta);

        // Setear saldo en movimiento y fecha actual si no tiene
        movimiento.setSaldo(nuevoSaldo);
        if (movimiento.getFecha() == null) {
            movimiento.setFecha(new Date());
        }

        return repositorioMovimiento.save(movimiento);
    }
}
