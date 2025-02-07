package com.alura.comex;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class VisualizadorInforme {
    private static final NumberFormat FORMATO_MONEDA =
            NumberFormat.getCurrencyInstance(new Locale("es", "CR"));

    public static void mostrar(CalculadoraEstadisticas.Estadisticas estadisticas) {
        System.out.println("#### INFORME DE VALORES TOTALES");
        System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %d\n", estadisticas.getTotalPedidos());
        System.out.printf("- TOTAL DE PRODUCTOS VENDIDOS: %d\n", estadisticas.getTotalProductos());
        System.out.printf("- TOTAL DE CATEGORÍAS: %d\n", estadisticas.getTotalCategorias());
        System.out.printf("- MONTO DE VENTAS: %s\n", formatearMoneda(estadisticas.getMontoTotal()));
        System.out.printf("- PEDIDO MAS BARATO: %s\n", formatearPedido(estadisticas.getPedidoMasBarato()));
        System.out.printf("- PEDIDO MAS CARO: %s\n", formatearPedido(estadisticas.getPedidoMasCaro()));

        System.out.println("\n#### PEDIDOS POR CLIENTE (ORDEN ALFABÉTICO)");
        estadisticas.getPedidosPorCliente().forEach((cliente, cantidad) ->
                System.out.printf("- %-20s: %d pedidos\n", cliente, cantidad)
        );
    }

    private static String formatearMoneda(BigDecimal valor) {
        return FORMATO_MONEDA.format(valor.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private static String formatearPedido(Pedido pedido) {
        return (pedido != null)
                ? String.format("%s (%s)", formatearMoneda(pedido.getValorTotal()), pedido.getProducto())
                : "N/A";
    }
}