package com.alura.comex;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CalculadoraProductosMasVendidos {

    public static List<ProductoMasVendido> calcular(List<Pedido> pedidos) {
        // Agrupa los pedidos por producto y suma las cantidades vendidas.
        Map<String, Integer> cantidadPorProducto = pedidos.stream()
                .collect(Collectors.groupingBy(
                        Pedido::getProducto,
                        Collectors.summingInt(Pedido::getCantidad)
                ));

        // Convierte el Map en una lista de ProductoMasVendido, ordena de forma descendente
        // y toma los tres primeros.
        List<ProductoMasVendido> topProductos = cantidadPorProducto.entrySet().stream()
                .map(e -> new ProductoMasVendido(e.getKey(), e.getValue()))
                .sorted((p1, p2) -> Integer.compare(p2.getCantidad(), p1.getCantidad()))
                .limit(3)
                .collect(Collectors.toList());
        return topProductos;
    }
}
