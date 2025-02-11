package com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Pedido {
    private final String categoria;
    private final String producto;
    private final String cliente;
    private final BigDecimal precio;
    private final int cantidad;
    private final LocalDate fecha;

    public Pedido(String categoria, String producto, String cliente,
                  BigDecimal precio, int cantidad, LocalDate fecha) {
        this.categoria = Objects.requireNonNull(categoria, "Categoría no puede ser nula");
        this.producto = Objects.requireNonNull(producto, "Producto no puede ser nulo");
        this.cliente = Objects.requireNonNull(cliente, "Cliente no puede ser nulo");
        this.precio = Objects.requireNonNull(precio, "Precio no puede ser nulo");
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad inválida: " + cantidad);
        this.cantidad = cantidad;
        this.fecha = Objects.requireNonNull(fecha, "Fecha no puede ser nula");
    }


    public BigDecimal getValorTotal() {
        return precio.multiply(BigDecimal.valueOf(cantidad));
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProducto() {
        return producto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}