package com.alura.comex;

import java.io.IOException;
import java.util.ArrayList;


public interface Procesador {
    ArrayList<Pedido> procesar(String tipoArchivo) throws IOException;
}
