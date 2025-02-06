package com.alura.comex;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class ProcesadorDeJson implements Procesador {

    @Override
    public ArrayList<Pedido> procesar(String tipoArchivo) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(tipoArchivo);

        if (inputStream == null) {
            throw new IOException("Archivo no encontrado: " + tipoArchivo);
        }

        PedidoJson[] pedidosJson;
        try {
            pedidosJson = objectMapper.readValue(inputStream, PedidoJson[].class);
        } catch (IOException e) {
            throw new IOException("Error al leer o procesar el archivo JSON", e);
        }

        // Convertir el arreglo de PedidoJson a una lista de objetos Pedido
        return (ArrayList<Pedido>) Arrays.stream(pedidosJson)
                .map(pedidoJson -> new Pedido(
                        pedidoJson.getCategoria(),
                        pedidoJson.getProducto(),
                        pedidoJson.getCliente(),
                        pedidoJson.getPrecio(),
                        pedidoJson.getCantidad(),
                        parseFecha(pedidoJson.getFecha())
                ))
                .toList();
    }

    // Método para parsear las fechas en formato "dd/MM/yyyy"
    private LocalDate parseFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(fecha, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha en formato incorrecto: " + fecha, e);
        }
    }


    private static class PedidoJson {
        private String categoria;
        private String producto;
        private BigDecimal precio;
        private int cantidad;
        private String fecha;
        private String cliente;

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }

        public BigDecimal getPrecio() {
            return precio;
        }

        public void setPrecio(BigDecimal precio) {
            this.precio = precio;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getCliente() {
            return cliente;
        }

        public void setCliente(String cliente) {
            this.cliente = cliente;
        }
    }
}
