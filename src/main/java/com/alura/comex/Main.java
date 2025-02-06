package com.alura.comex;

import java.util.*;

public class Main {

    public static void main(String[] args) {


            ProcesadorDeCsv procesadorDeCsv = new ProcesadorDeCsv();
            ArrayList<Pedido> pedidos = procesadorDeCsv.procesar("pedidos.csv");
            ProcesadorDeJson procesadorDeJson = new ProcesadorDeJson();
           // ArrayList<Pedido> pedidos = procesadorDeJson.procesar("pedidos.json");

            InformeSintetico Sintetico = new InformeSintetico(pedidos);


            System.out.println("#### INFORME DE VALORES TOTALES");
            System.out.printf("- TOTAL DE PEDIDOS REALIZADOS: %s\n", Sintetico.getTotalDePedidosRealizados());
            System.out.printf("- TOTAL DE PRODUCTOS VENDIDOS: %s\n", Sintetico.getTotalDeProductosVendidos());
            System.out.printf("- TOTAL DE CATEGORIAS: %s\n", Sintetico.getTotalDeCategorias());
            System.out.printf("- MONTO DE VENTAS: %s\n", Sintetico.getMontoDeVentas());
            System.out.printf("- PEDIDO MAS BARATO: %s (%s)\n", Sintetico.getPedidoMasBaratoPrecio(), Sintetico.getPedidoMasBaratoProducto());
            System.out.printf("- PEDIDO MAS CARO: %s (%s)\n", Sintetico.getPedidoMasCaroPrecio(), Sintetico.getPedidoMasCaroProducto());

        }
    }

