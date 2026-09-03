package com.example.practica1algoritmos.vista.gui;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class SecciónAcciones {
    private final int ALTO_BOTON = 40;
    private final int ANCHO_BOTON = 40;

    private ImageButton botonPedirCarta;
    private ImageButton botonPlantarse;
    private HBox contenedor;

    public SecciónAcciones() {
        botonPedirCarta = new ImageButton("Pedir otra carta", "/recursos/iconos/placeholder.png");
        botonPlantarse = new ImageButton("Plantarse", "/recursos/iconos/placeholder.png");

        contenedor = new HBox(15, botonPedirCarta, botonPlantarse);
        contenedor.setAlignment(Pos.CENTER);
    }

    public HBox getContenedor() {
        return contenedor;
    }

    public void alPedirCarta(Runnable accion) {
        botonPlantarse.setOnAction(e -> accion.run());
    }

    public void alPlantarse(Runnable accion) {
        botonPlantarse.setOnAction(e -> accion.run());
    }

    public void habilitarBotones(boolean estaHabilitado) {
        botonPedirCarta.setDisable(!estaHabilitado);
        botonPlantarse.setDisable(!estaHabilitado);
    }
}
