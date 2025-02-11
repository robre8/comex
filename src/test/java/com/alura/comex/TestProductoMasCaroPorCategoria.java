package com.alura.comex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestProductoMasCaroPorCategoria {

    @Test
    public void testCalcularDesdeArchivo() throws Exception {

        Procesador procesador = ProcesadorFactory.crear("csv");
        ArrayList<Pedido> pedidos = procesador.procesar("pedidos.csv");

        List<ProductoMasCaroPorCategoria> informe = CalculadoraProductoMasCaroPorCategoria.calcular(pedidos);

        assertNotNull(informe, "El informe no debe ser nulo");
        assertFalse(informe.isEmpty(), "El informe no debe estar vacío");

        for (int i = 0; i < informe.size() - 1; i++) {
            String catActual = informe.get(i).getCategoria();
            String catSiguiente = informe.get(i + 1).getCategoria();
            assertTrue(catActual.compareTo(catSiguiente) <= 0,
                    "Las categorías deben estar ordenadas alfabéticamente");
        }

        // Verificar la categoría AUTOMOTOR.
        ProductoMasCaroPorCategoria automotor = informe.stream()
                .filter(item -> "AUTOMOTOR".equals(item.getCategoria()))
                .findFirst()
                .orElse(null);
        assertNotNull(automotor, "La categoría AUTOMOTOR debe estar presente");
        assertEquals("Juego de neumáticos", automotor.getProducto(),
                "El producto de la categoría AUTOMOTOR no es el esperado");
        // Se asume que el precio esperado es 219024.52 (usar compareTo para comparar BigDecimal)
        assertEquals(0, automotor.getPrecio().compareTo(new BigDecimal("219024.52")),
                "El precio para AUTOMOTOR no es el esperado");

        // Verificar la categoría CELULARES.
        ProductoMasCaroPorCategoria celulares = informe.stream()
                .filter(item -> "CELULARES".equals(item.getCategoria()))
                .findFirst()
                .orElse(null);
        assertNotNull(celulares, "La categoría CELULARES debe estar presente");
        assertEquals("iPhone 16 Pro", celulares.getProducto(),
                "El producto de la categoría CELULARES no es el esperado");
        assertEquals(0, celulares.getPrecio().compareTo(new BigDecimal("1574079.55")),
                "El precio para CELULARES no es el esperado");
    }

    @Test
    public void testCalcularConUnSoloPedido() {

        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido = new Pedido(
                "TestCat",
                "TestProduct",
                "ClienteTest",
                new BigDecimal("150.00"),
                2,
                LocalDate.now()
        );
        pedidos.add(pedido);

        List<ProductoMasCaroPorCategoria> informe = CalculadoraProductoMasCaroPorCategoria.calcular(pedidos);

        assertNotNull(informe, "El informe no debe ser nulo");
        assertEquals(1, informe.size(), "El informe debe contener un solo elemento");
        ProductoMasCaroPorCategoria item = informe.get(0);
        assertEquals("TestCat", item.getCategoria(), "La categoría debe ser 'TestCat'");
        assertEquals("TestProduct", item.getProducto(), "El producto debe ser 'TestProduct'");
        assertEquals(0, item.getPrecio().compareTo(new BigDecimal("150.00")),
                "El precio debe ser 150.00");
    }

    @Test
    public void testCalcularConListaVacia() {

        List<Pedido> pedidos = new ArrayList<>();

        List<ProductoMasCaroPorCategoria> informe = CalculadoraProductoMasCaroPorCategoria.calcular(pedidos);

        assertNotNull(informe, "El informe no debe ser nulo");
        assertTrue(informe.isEmpty(), "El informe debe estar vacío cuando no hay pedidos");
    }
}

