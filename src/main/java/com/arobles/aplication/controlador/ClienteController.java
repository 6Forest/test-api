
package com.arobles.aplication.controlador;


import com.arobles.aplication.modelo.Cliente;
import com.arobles.aplication.servicio.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ServicioCliente servicioCliente;

    @GetMapping
    public Iterable<Cliente> listar() {
        return servicioCliente.listar();
    }

    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente) {
        return servicioCliente.guardar(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable Integer id) {
        Optional<Cliente> cliente = servicioCliente.buscar(id);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer id, @RequestBody Cliente nuevo) {
        if (servicioCliente.buscar(id).isPresent()) {
            nuevo.setClienteId(id);
            return ResponseEntity.ok(servicioCliente.guardar(nuevo));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        servicioCliente.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}