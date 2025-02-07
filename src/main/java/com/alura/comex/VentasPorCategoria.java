package com.alura.comex;

import java.math.BigDecimal;

public class VentasPorCategoria {
    private final String categoria;
    private final int cantidadVendida;
    private final BigDecimal montoVentas;

    public VentasPorCategoria(String categoria, int cantidadVendida, BigDecimal montoVentas) {
        this.categoria = categoria;
        this.cantidadVendida = cantidadVendida;
        this.montoVentas = montoVentas;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public BigDecimal getMontoVentas() {
        return montoVentas;
    }
}
