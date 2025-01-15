package com.alura.comex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

public class InformeSintetico {


    private int totalDeProductosVendidos;
    private int totalDePedidosRealizados;
    private BigDecimal montoDeVentas = BigDecimal.ZERO;
    private Pedido pedidoMasBarato;
    private Pedido pedidoMasCaro;

    private final HashSet<String> categoriasProcesadas  = new HashSet<>();

    private int totalDeCategorias;

    public InformeSintetico(ArrayList<Pedido> pedidos) {


        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoActual = pedidos.get(i);

            if (pedidoActual == null) {
                break;
            }

            if (pedidoMasBarato == null || pedidoActual.getPrecio().multiply(new BigDecimal(pedidoActual.getCantidad())).compareTo(pedidoMasBarato.getPrecio().multiply(new BigDecimal(pedidoMasBarato.getCantidad()))) < 0) {
                pedidoMasBarato = pedidoActual;
            }

            if (pedidoMasCaro == null || pedidoActual.getPrecio().multiply(new BigDecimal(pedidoActual.getCantidad())).compareTo(pedidoMasCaro.getPrecio().multiply(new BigDecimal(pedidoMasCaro.getCantidad()))) > 0) {
                pedidoMasCaro = pedidoActual;
            }

            montoDeVentas = montoDeVentas.add(pedidoActual.getPrecio().multiply(new BigDecimal(pedidoActual.getCantidad())));
            totalDeProductosVendidos += pedidoActual.getCantidad();
            totalDePedidosRealizados++;

            if (!categoriasProcesadas.contains(pedidoActual.getCategoria())) {
                totalDeCategorias++;
                categoriasProcesadas.add(pedidoActual.getCategoria());
            }
        }
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