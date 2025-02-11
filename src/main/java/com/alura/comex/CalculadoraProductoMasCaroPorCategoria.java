package com.alura.comex;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CalculadoraProductoMasCaroPorCategoria {

    public static List<ProductoMasCaroPorCategoria> calcular(List<Pedido> pedidos) {

        Map<String, List<Pedido>> pedidosPorCategoria = pedidos.stream()
                .collect(Collectors.groupingBy(
                        Pedido::getCategoria,
                        TreeMap::new,
                        Collectors.toList()
                ));


        List<ProductoMasCaroPorCategoria> lista = pedidosPorCategoria.entrySet().stream()
                .map(entry -> {
                    String categoria = entry.getKey();
                    Pedido pedidoMasCaro = entry.getValue().stream()
                            .max((p1, p2) -> p1.getPrecio().compareTo(p2.getPrecio()))
                            .orElse(null);
                    if (pedidoMasCaro != null) {
                        return new ProductoMasCaroPorCategoria(
                                categoria,
                                pedidoMasCaro.getProducto(),
                                pedidoMasCaro.getPrecio()
                        );
                    }
                    return null;
                })
                .filter(item -> item != null)
                .collect(Collectors.toList());

        return lista;
    }
}
