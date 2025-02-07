package com.alura.comex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;


class TestVentasPorCategoria {

    @InjectMocks
    private InformePorCategorias informeVentasPorCategoria;

    private List<Object[]> ventascategorias;

    private ProcesadorDeCsv procesadorDeCsv;
    private List<Pedido> pedidos;
    @Test
    public void generaInformeDelCSV(){

        ventascategorias = new ArrayList<>();
        procesadorDeCsv = new ProcesadorDeCsv();
        pedidos = procesadorDeCsv.DevolverPedido();
        informeVentasPorCategoria = new informeVentasPorCategoria();

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoActual = pedidos.get(i);
            informeVentasPorCategoria.setVentasPorCategoria(pedidoActual,ventascategorias);
        }

        Assertions.assertNotNull(informeVentasPorCategoria.getInformeCategorias(ventascategorias));


    }

    @Test
    public void noDebeGenerarElInforme(){

        ventascategorias = new ArrayList<>();
        procesadorDeCsv = new ProcesadorDeCsv();
        pedidos = List.of();
        informeVentasPorCategoria = new informeVentasPorCategoria();

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedidoActual = pedidos.get(i);
           informeVentasPorCategoria.setVentasPorCategoria(pedidoActual,ventascategorias);
        }

        Assertions.assertThrows(noExisteArchivoException.class , () -> informeVentasPorCategoria.Getventascategorias(ventascategorias));


    }

}