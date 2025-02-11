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
    public ArrayList<Pedido> procesar(String rutaArchivo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(rutaArchivo)) {
            if (input == null) throw new IOException("Archivo no encontrado: " + rutaArchivo);

            PedidoDTO[] pedidosDTO = mapper.readValue(input, PedidoDTO[].class);
            return new ArrayList<>(Arrays.stream(pedidosDTO)
                    .map(this::mapearAPedido)
                    .toList());
        }
    }

    private Pedido mapearAPedido(PedidoDTO dto) {
        return new Pedido(
                dto.getCategoria(),
                dto.getProducto(),
                dto.getCliente(),
                dto.getPrecio(),
                dto.getCantidad(),
                LocalDate.parse(dto.getFecha(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );
    }

    // Clase DTO interna
    private static class PedidoDTO {
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