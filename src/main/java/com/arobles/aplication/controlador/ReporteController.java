
package com.arobles.aplication.controlador;

import com.arobles.aplication.dto.ReporteEstadoCuentaDTO;
import com.arobles.aplication.servicio.ServicioReporte;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ServicioReporte servicioReporte;

    @GetMapping
    public ResponseEntity<?> obtenerReporte(
            @RequestParam Integer clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        try {
            ReporteEstadoCuentaDTO reporte = servicioReporte.generarReporte(clienteId, fechaInicio, fechaFin);
            return ResponseEntity.ok(reporte);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
