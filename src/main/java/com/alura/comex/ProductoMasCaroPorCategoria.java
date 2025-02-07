package com.alura.comex;

import java.math.BigDecimal;

public class ProductoMasCaroPorCategoria {
    private final String categoria;
    private final String producto;
    private final BigDecimal precio;

    public ProductoMasCaroPorCategoria(String categoria, String producto, BigDecimal precio) {
        this.categoria = categoria;
        this.producto = producto;
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProducto() {
        return producto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }
}
