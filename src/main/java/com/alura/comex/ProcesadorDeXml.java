package com.alura.comex;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;


public class ProcesadorDeXml implements Procesador {

    @Override
    public ArrayList<Pedido> procesar(String tipoArchivo) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(tipoArchivo);

        if (inputStream == null) {
            throw new RuntimeException("Archivo no encontrado!");
        }

        PedidoXml[] pedidosXml = xmlMapper.readValue(inputStream, PedidoXml[].class);
        return (ArrayList<Pedido>) Arrays.stream(pedidosXml)
                .map(pedidoXml -> new Pedido(
                        pedidoXml.getCategoria(),
                        pedidoXml.getProducto(),
                        pedidoXml.getCliente(),
                        pedidoXml.getPrecio(),
                        pedidoXml.getCantidad(),
                        LocalDate.parse(pedidoXml.getFecha(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                ))
                .toList();
    }

    private static class PedidoXml {
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