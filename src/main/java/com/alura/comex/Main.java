package com.alura.comex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Procesador procesador = ProcesadorFactory.crear("csv");
            ArrayList<Pedido> pedidos = procesador.procesar("pedidos.csv");

            // Informe de valores totales
            CalculadoraEstadisticas.Estadisticas stats =
                    CalculadoraEstadisticas.calcular(pedidos);
            VisualizadorInforme.mostrar(stats);


        } catch (IOException e) {
            System.err.println("Error procesando archivo: " + e.getMessage());
        }
    }
}

