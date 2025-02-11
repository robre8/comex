package com.alura.comex;

public class ProcesadorFactory {
    public static Procesador crear(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "csv" -> new ProcesadorDeCsv();
            case "json" -> new ProcesadorDeJson();
            case "xml" -> new ProcesadorDeXml();
            default -> throw new IllegalArgumentException("Formato no soportado: " + tipo);
        };
    }
}