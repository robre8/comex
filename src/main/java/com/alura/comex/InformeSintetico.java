package com.alura.comex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class InformeSintetico {


    private final int totalDeProductosVendidos;
    private final int totalDePedidosRealizados;
    private final BigDecimal montoDeVentas;
    private Pedido pedidoMasBarato;
    private Pedido pedidoMasCaro;

    private final HashSet<String> categoriasProcesadas  = new HashSet<>();

    private int totalDeCategorias;

    public InformeSintetico(ArrayList<Pedido> pedidos) {


        totalDePedidosRealizados = pedidos.size();

        montoDeVentas = pedidos.stream()
                .filter(pedido -> pedido != null)
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        totalDeProductosVendidos = pedidos.stream()
                .filter(pedido -> pedido != null)
                .mapToInt(Pedido::getCantidad)
                .sum();

        totalDeCategorias = (int) pedidos.stream()
                .filter(pedido -> pedido != null)
                .map(Pedido::getCategoria)
                .distinct()
                .count();

        pedidos.stream()
                .filter(pedido -> pedido != null)
                .forEach(pedidoActual -> {


                    if (pedidoMasBarato == null || pedidoActual.isMasBaratoQue(pedidoMasBarato)) {
                        pedidoMasBarato = pedidoActual;
                    }

                    if (pedidoMasCaro == null || pedidoActual.isMasCaroQue(pedidoMasCaro)) {
                        pedidoMasCaro = pedidoActual;
                    }
                });
    }


    public int getTotalDeProductosVendidos() {
        return totalDeProductosVendidos;
    }

    public int getTotalDePedidosRealizados() {
        return totalDePedidosRealizados;
    }

    public String getMontoDeVentas() {
        return NumberFormat.getCurrencyInstance(new Locale("es","CR")).format(montoDeVentas.setScale(2, RoundingMode.HALF_DOWN));
    }

    public String getPedidoMasBaratoPrecio() {
        return NumberFormat.getCurrencyInstance(new Locale("es", "CR")).format(pedidoMasBarato.getPrecio().multiply(new BigDecimal(pedidoMasBarato.getCantidad())).setScale(2, RoundingMode.HALF_DOWN));
    }

    public String getPedidoMasBaratoProducto(){
        return pedidoMasBarato.getProducto();
    }

    public String getPedidoMasCaroPrecio() {
        return NumberFormat.getCurrencyInstance(new Locale("es", "CR")).format(pedidoMasCaro.getPrecio().multiply(new BigDecimal(pedidoMasCaro.getCantidad())).setScale(2, RoundingMode.HALF_DOWN));
    }
    public String getPedidoMasCaroProducto(){
        return pedidoMasCaro.getProducto();
    }

    public int getTotalDeCategorias() {
        return totalDeCategorias;
    }


}