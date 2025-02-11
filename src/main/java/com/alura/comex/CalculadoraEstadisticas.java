package com.alura.comex;

import java.util.TreeMap;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CalculadoraEstadisticas {

    public static Estadisticas calcular(List<Pedido> pedidos) {
        int totalPedidos = pedidos.size();
        int totalProductos = pedidos.stream().mapToInt(Pedido::getCantidad).sum();
        BigDecimal montoTotal = pedidos.stream()
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long totalCategorias = pedidos.stream()
                .map(Pedido::getCategoria)
                .distinct()
                .count();

        Map<String, Long> pedidosPorCliente = pedidos.stream()
                .collect(Collectors.groupingBy(
                        Pedido::getCliente,
                        TreeMap::new, // Ordena automáticamente por clave
                        Collectors.counting()));

        Optional<Pedido> masBarato = pedidos.stream()
                .min((p1, p2) -> p1.getValorTotal().compareTo(p2.getValorTotal()));

        Optional<Pedido> masCaro = pedidos.stream()
                .max((p1, p2) -> p1.getValorTotal().compareTo(p2.getValorTotal()));

        return new Estadisticas(totalPedidos, totalProductos, montoTotal,totalCategorias,
                masBarato.orElse(null), masCaro.orElse(null), pedidosPorCliente);


    }

    public static class Estadisticas {
        private final int totalPedidos;
        private final int totalProductos;
        private long totalCategorias;
        private final BigDecimal montoTotal;
        private final Pedido pedidoMasBarato;
        private final Pedido pedidoMasCaro;
        private final Map<String, Long> pedidosPorCliente;



        public Estadisticas(int totalPedidos, int totalProductos, BigDecimal montoTotal,
                            long totalCategorias, Pedido pedidoMasBarato, Pedido pedidoMasCaro,
                            Map<String, Long> pedidosPorCliente) {
            this.totalPedidos = totalPedidos;
            this.totalProductos = totalProductos;
            this.totalCategorias = totalCategorias;
            this.montoTotal = montoTotal;
            this.pedidoMasBarato = pedidoMasBarato;
            this.pedidoMasCaro = pedidoMasCaro;
            this.pedidosPorCliente = pedidosPorCliente;

        }

        public Map<String, Long> getPedidosPorCliente() {
            return pedidosPorCliente;
        }

        public long getTotalCategorias() {
            return totalCategorias;
        }

        public int getTotalPedidos() {
            return totalPedidos;
        }

        public int getTotalProductos() {
            return totalProductos;
        }

        public BigDecimal getMontoTotal() {
            return montoTotal;
        }

        public Pedido getPedidoMasBarato() {
            return pedidoMasBarato;
        }

        public Pedido getPedidoMasCaro() {
            return pedidoMasCaro;
        }
    }
}