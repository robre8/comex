package com.alura.comex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestProductosMasVendidos {

    @Test
    public void testCalcularProductosMasVendidosDesdeArchivo() throws Exception {

        Procesador procesador = ProcesadorFactory.crear("csv");
        ArrayList<Pedido> pedidos = procesador.procesar("pedidos.csv");

        List<ProductoMasVendido> productosMasVendidos = CalculadoraProductosMasVendidos.calcular(pedidos);

        assertNotNull(productosMasVendidos, "El informe no debe ser nulo");
        assertFalse(productosMasVendidos.isEmpty(), "El informe no debe estar vacío");

        assertTrue(productosMasVendidos.size() <= 3, "El informe debe contener como máximo tres productos");

        // A modo de ejemplo, se asume que el primer producto es "iPhone 16 Pro" con cantidad 6.
        ProductoMasVendido primerProducto = productosMasVendidos.get(0);
        assertEquals("iPhone 16 Pro", primerProducto.getProducto(),
                "El primer producto debería ser iPhone 16 Pro");
        assertEquals(6, primerProducto.getCantidad(),
                "La cantidad vendida para iPhone 16 Pro debería ser 6");
    }

    @Test
    public void testCalcularProductosMasVendidosConUnSoloPedido() {

        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido = new Pedido(
                "CategoriaTest",
                "Producto único",
                "ClienteTest",
                new BigDecimal("100.00"),
                3,
                LocalDate.now()
        );
        pedidos.add(pedido);

        List<ProductoMasVendido> productosMasVendidos = CalculadoraProductosMasVendidos.calcular(pedidos);

        assertNotNull(productosMasVendidos, "El informe no debe ser nulo");
        assertEquals(1, productosMasVendidos.size(), "El informe debe contener un solo producto");
        ProductoMasVendido producto = productosMasVendidos.get(0);
        assertEquals("Producto único", producto.getProducto(),
                "El nombre del producto debería ser 'Producto único'");
        assertEquals(3, producto.getCantidad(),
                "La cantidad vendida debería ser 3");
    }
}
