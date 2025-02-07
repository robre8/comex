package com.alura.comex;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcesadorDeCsv implements Procesador {

    @Override
    public ArrayList<Pedido> procesar(String rutaArchivo) throws IOException {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            URL recurso = ClassLoader.getSystemResource(rutaArchivo);
            Path ruta = Path.of(recurso.toURI());

            try (Scanner scanner = new Scanner(ruta)) {
                scanner.nextLine(); // Saltar cabecera
                while (scanner.hasNextLine()) {
                    String[] campos = scanner.nextLine().split(",");
                    pedidos.add(mapearAPedido(campos));
                }
            }
        } catch (URISyntaxException e) {
            throw new IOException("Error en la ruta del archivo: " + rutaArchivo, e);
        }
        return pedidos;
    }

    private Pedido mapearAPedido(String[] campos) {
        String categoria = campos[0];
        String producto = campos[1];
        BigDecimal precio = new BigDecimal(campos[2]);
        int cantidad = Integer.parseInt(campos[3]);
        LocalDate fecha = LocalDate.parse(campos[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String cliente = campos[5];

        return new Pedido(categoria, producto, cliente, precio, cantidad, fecha);
    }
}