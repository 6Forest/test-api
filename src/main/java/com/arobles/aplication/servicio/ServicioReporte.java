package com.arobles.aplication.servicio;

import com.arobles.aplication.dto.CuentaConMovimientosDTO;
import com.arobles.aplication.dto.MovimientoDTO;
import com.arobles.aplication.dto.ReporteEstadoCuentaDTO;
import com.arobles.aplication.modelo.Cliente;
import com.arobles.aplication.modelo.Cuenta;
import com.arobles.aplication.modelo.Movimiento;
import com.arobles.aplication.repositorio.RepositorioCliente;
import com.arobles.aplication.repositorio.RepositorioCuenta;
import com.arobles.aplication.repositorio.RepositorioMovimiento;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioReporte {

    @Autowired
    private RepositorioCliente repositorioCliente;

    @Autowired
    private RepositorioCuenta repositorioCuenta;

    @Autowired
    private RepositorioMovimiento repositorioMovimiento;

    public ReporteEstadoCuentaDTO generarReporte(Integer clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        Cliente cliente = repositorioCliente.findById(clienteId).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        // Obtener cuentas del cliente
        List<Cuenta> cuentas = repositorioCuenta.findByCliente(cliente);

        List<CuentaConMovimientosDTO> cuentasDTO = cuentas.stream().map(cuenta -> {
            // Obtener movimientos en el rango de fechas para la cuenta
            LocalDateTime inicio = fechaInicio.atStartOfDay();
            LocalDateTime fin = fechaFin.atTime(LocalTime.MAX);

            List<Movimiento> movimientos = repositorioMovimiento.findByCuentaAndFechaBetween(cuenta, inicio, fin);

            List<MovimientoDTO> movimientosDTO = movimientos.stream().map(mov -> new MovimientoDTO(
                    mov.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                    mov.getTipoMovimiento(),
                    mov.getValor(),
                    mov.getSaldo()
            )).collect(Collectors.toList());

            return new CuentaConMovimientosDTO(
                    cuenta.getNumeroCuenta(),
                    cuenta.getTipoCuenta(),
                    cuenta.getSaldoInicial(), // saldo actual o saldo inicial?
                    movimientosDTO
            );
        }).collect(Collectors.toList());

        ReporteEstadoCuentaDTO reporte = new ReporteEstadoCuentaDTO();
        reporte.setClienteId(cliente.getClienteId());
        reporte.setNombreCliente(cliente.getPersona().getNombre());
        reporte.setCuentas(cuentasDTO);

        return reporte;
    }
}
