
package com.arobles.aplication.dto;

import java.util.List;

public class ReporteEstadoCuentaDTO {
    private Integer clienteId;
    private String nombreCliente;
    private List<CuentaConMovimientosDTO> cuentas;

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public List<CuentaConMovimientosDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaConMovimientosDTO> cuentas) {
        this.cuentas = cuentas;
    }
}