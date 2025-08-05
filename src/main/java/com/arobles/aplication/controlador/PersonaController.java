
package com.arobles.aplication.controlador;

import com.arobles.aplication.modelo.Persona;
import com.arobles.aplication.servicio.ServicioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private ServicioPersona servicioPersona;

    @GetMapping
    public Iterable<Persona> listar() {
        return servicioPersona.listar();
    }

    @PostMapping
    public Persona guardar(@RequestBody Persona persona) {
        return servicioPersona.guardar(persona);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtener(@PathVariable Integer id) {
        Optional<Persona> persona = servicioPersona.buscar(id);
        return persona.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizar(@PathVariable Integer id, @RequestBody Persona nueva) {
        if (servicioPersona.buscar(id).isPresent()) {
            nueva.setIdPersona(id);
            return ResponseEntity.ok(servicioPersona.guardar(nueva));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        servicioPersona.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}