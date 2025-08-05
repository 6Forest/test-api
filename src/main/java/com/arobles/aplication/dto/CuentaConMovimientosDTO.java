package com.arobles.aplication.dto;

import java.math.BigDecimal;
import java.util.List;

public class CuentaConMovimientosDTO {

    private Long numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldo;
    private List<MovimientoDTO> movimientos;

    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<MovimientoDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoDTO> movimientos) {
        this.movimientos = movimientos;
    }

    public CuentaConMovimientosDTO(Long numeroCuenta, String tipoCuenta, BigDecimal saldo, List<MovimientoDTO> movimientos) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.movimientos = movimientos;
    }
}
