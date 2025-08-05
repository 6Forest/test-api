
package com.arobles.aplication.controlador;

import com.arobles.aplication.modelo.Cuenta;
import com.arobles.aplication.servicio.ServicioCuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private ServicioCuenta servicioCuenta;

    @GetMapping
    public Iterable<Cuenta> listar() {
        return servicioCuenta.listar();
    }

    @PostMapping
    public Cuenta guardar(@RequestBody Cuenta cuenta) {
        return servicioCuenta.guardar(cuenta);
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Cuenta> obtener(@PathVariable Long numero) {
        Optional<Cuenta> cuenta = servicioCuenta.buscar(numero);
        return cuenta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{numero}")
    public ResponseEntity<Cuenta> actualizar(@PathVariable Long numero, @RequestBody Cuenta nueva) {
        if (servicioCuenta.buscar(numero).isPresent()) {
            nueva.setNumeroCuenta(numero.longValue());
            return ResponseEntity.ok(servicioCuenta.guardar(nueva));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> eliminar(@PathVariable Long numero) {
        servicioCuenta.eliminar(numero);
        return ResponseEntity.noContent().build();
    }
}