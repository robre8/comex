package com.alura.comex;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


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

    // método que muestra el informe de ventas por categoría
    public static void mostrar(CalculadoraEstadisticas.Estadisticas estadisticas, List<VentasPorCategoria> ventasPorCategoria) {

        System.out.println("\n#### INFORME DE VENTAS POR CATEGORÍA");
        for (VentasPorCategoria venta : ventasPorCategoria) {
            System.out.printf("CATEGORÍA: %s\n", venta.getCategoria());
            System.out.printf("CANTIDAD VENDIDA: %d\n", venta.getCantidadVendida());
            System.out.printf("MONTO: %s\n\n", formatearMoneda(venta.getMontoVentas()));
        }
    }
    // Método que muestra el informe de productos más vendidos
    public static void mostrar(CalculadoraEstadisticas.Estadisticas estadisticas,
                               List<VentasPorCategoria> ventasPorCategoria,
                               List<ProductoMasVendido> productosMasVendidos) {
        mostrar(estadisticas, ventasPorCategoria);
        // Sección de productos más vendidos.
        System.out.println("\n#### INFORME DE PRODUCTOS MÁS VENDIDOS");
        for (ProductoMasVendido p : productosMasVendidos) {
            System.out.printf("PRODUCTO: %s\n", p.getProducto());
            System.out.printf("CANTIDAD: %d\n\n", p.getCantidad());
        }
    }

    // Método que añade al informe la sección de productos más caros por categoría
    public static void mostrar(CalculadoraEstadisticas.Estadisticas estadisticas,
                               List<VentasPorCategoria> ventasPorCategoria,
                               List<ProductoMasVendido> productosMasVendidos,
                               List<ProductoMasCaroPorCategoria> productosMasCaros) {
        // Se muestra primero el informe completo
        mostrar(estadisticas, ventasPorCategoria, productosMasVendidos);


        System.out.println("\n#### INFORME DE PRODUCTOS MÁS CAROS POR CATEGORÍA");
        for (ProductoMasCaroPorCategoria item : productosMasCaros) {
            System.out.printf("CATEGORIA: %s\n", item.getCategoria());
            System.out.printf("PRODUCTO: %s\n", item.getProducto());
            System.out.printf("PRECIO: %s\n\n", formatearMoneda(item.getPrecio()));
        }
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