package com.alura.comex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

public class TestVentasPorCategoria {

    @Test
    public void testCalcularVentasPorCategoriaDesdeArchivo() throws Exception {

        Procesador procesador = ProcesadorFactory.crear("csv");

        ArrayList<Pedido> pedidos = procesador.procesar("pedidos.csv");

        List<VentasPorCategoria> ventas = CalculadoraVentasPorCategoria.calcular(pedidos);

        assertNotNull(ventas, "El informe de ventas por categoría no debe ser nulo");
        assertFalse(ventas.isEmpty(), "El informe de ventas por categoría no debe estar vacío");

        // Verificar categoria AUTOMOTOR
        VentasPorCategoria automotor = ventas.stream()
                .filter(v -> "AUTOMOTOR".equals(v.getCategoria()))
                .findFirst()
                .orElse(null);
        assertNotNull(automotor, "La categoría AUTOMOTOR debe existir");
        assertEquals(2, automotor.getCantidadVendida(), "Cantidad vendida para AUTOMOTOR incorrecta");
        assertEquals(0, automotor.getMontoVentas().compareTo(new BigDecimal("341022.55")),
                "Monto vendido para AUTOMOTOR incorrecto");

        // Verificar la categoría CELULARES.
        VentasPorCategoria celulares = ventas.stream()
                .filter(v -> "CELULARES".equals(v.getCategoria()))
                .findFirst()
                .orElse(null);
        assertNotNull(celulares, "La categoría CELULARES debe existir");
        assertEquals(11, celulares.getCantidadVendida(), "Cantidad vendida para CELULARES incorrecta");
        assertEquals(1, celulares.getMontoVentas().compareTo(new BigDecimal("3040618.73")),
                "Monto vendido para CELULARES incorrecto");

        for (int i = 0; i < ventas.size() - 1; i++) {
            String catActual = ventas.get(i).getCategoria();
            String catSiguiente = ventas.get(i + 1).getCategoria();
            assertTrue(catActual.compareTo(catSiguiente) <= 0,
                    "Las categorías deben estar ordenadas alfabéticamente");
        }
    }


    @Test
    public void testCalcularVentasPorCategoriaListaVacia() {

        List<Pedido> pedidosVacios = new ArrayList<>();

        List<VentasPorCategoria> ventas = CalculadoraVentasPorCategoria.calcular(pedidosVacios);

        assertNotNull(ventas, "El informe no debe ser nulo");
        assertTrue(ventas.isEmpty(), "El informe de ventas por categoría debe estar vacío cuando no hay pedidos");
    }
}
