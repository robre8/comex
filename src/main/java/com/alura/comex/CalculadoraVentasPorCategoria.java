package com.alura.comex;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CalculadoraVentasPorCategoria {

    public static List<VentasPorCategoria> calcular(List<Pedido> pedidos) {
        // Agrupa los pedidos por categoría utilizando TreeMap para orden alfabético.
        Map<String, List<Pedido>> pedidosPorCategoria = pedidos.stream()
                .collect(Collectors.groupingBy(
                        Pedido::getCategoria,
                        TreeMap::new, // Ordena las claves alfabéticamente
                        Collectors.toList()
                ));

        // Mapea cada grupo a un objeto VentasPorCategoria
        List<VentasPorCategoria> ventasPorCategoria = pedidosPorCategoria.entrySet().stream()
                .map(entry -> {
                    String categoria = entry.getKey();
                    int cantidadVendida = entry.getValue().stream()
                            .mapToInt(Pedido::getCantidad)
                            .sum();
                    BigDecimal montoVentas = entry.getValue().stream()
                            .map(Pedido::getValorTotal)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    return new VentasPorCategoria(categoria, cantidadVendida, montoVentas);
                })
                .collect(Collectors.toList());

        return ventasPorCategoria;
    }
}
