package com.arobles.aplication.controlador;

import com.arobles.aplication.modelo.Movimiento;
import com.arobles.aplication.servicio.ServicioMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private ServicioMovimiento servicioMovimiento;

    @GetMapping
    public Iterable<Movimiento> listar() {
        return servicioMovimiento.listar();
    }

    @PostMapping
    public ResponseEntity<Movimiento> guardar(@RequestBody Movimiento movimiento) {
        Movimiento movimientoGuardado = servicioMovimiento.guardar(movimiento);
        return ResponseEntity.ok(movimientoGuardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtener(@PathVariable Integer id) {
        Optional<Movimiento> movimiento = servicioMovimiento.buscar(id);
        return movimiento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizar(@PathVariable Integer id, @RequestBody Movimiento nuevo) {
        if (servicioMovimiento.buscar(id).isPresent()) {
            nuevo.setIdMovimiento(id);
            return ResponseEntity.ok(servicioMovimiento.guardar(nuevo));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        servicioMovimiento.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
