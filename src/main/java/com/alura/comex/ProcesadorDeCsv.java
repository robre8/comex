package com.alura.comex;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcesadorDeCsv implements Procesador {

    @Override
    public ArrayList<Pedido> procesar(String rutaArchivo) throws IOException {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(rutaArchivo)) {
            if (inputStream == null) {
                throw new IOException("No se pudo encontrar el archivo: " + rutaArchivo);
            }

            try (Scanner scanner = new Scanner(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                scanner.nextLine();
                while (scanner.hasNextLine()) {
                    String[] campos = scanner.nextLine().split(",");
                    pedidos.add(mapearAPedido(campos));
                }
            }
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