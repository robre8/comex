package com.alura.comex;

public class ProductoMasVendido {
    private final String producto;
    private final int cantidad;

    public ProductoMasVendido(String producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}
