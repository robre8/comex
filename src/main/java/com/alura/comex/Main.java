package com.alura.comex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            Procesador procesador = ProcesadorFactory.crear("csv");
            ArrayList<Pedido> pedidos = procesador.procesar("pedidos.csv");

            // Informe de valores totales
            CalculadoraEstadisticas.Estadisticas stats =
                    CalculadoraEstadisticas.calcular(pedidos);
            VisualizadorInforme.mostrar(stats);

            // Cálculo del informe de ventas por categoría
            List<VentasPorCategoria> ventasPorCategoria = CalculadoraVentasPorCategoria.calcular(pedidos);

            // Calcular el informe de productos más vendidos.
            List<ProductoMasVendido> productosMasVendidos = CalculadoraProductosMasVendidos.calcular(pedidos);

            // Calcular el informe de productos más caros por categoría.
            List<ProductoMasCaroPorCategoria> productosMasCaros = CalculadoraProductoMasCaroPorCategoria.calcular(pedidos);

            // Mostrar el informe completo: estadísticas generales, ventas por categoría,
            // productos más vendidos y productos más caros por categoría.
            VisualizadorInforme.mostrar(stats, ventasPorCategoria, productosMasVendidos, productosMasCaros);


        } catch (IOException e) {
            System.err.println("Error procesando archivo: " + e.getMessage());
        }
    }
}

