package com.example.practica1algoritmos.vista.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class SecciónAcciones {
    private final int ALTO_BOTON = 77;
    private final int ANCHO_BOTON = 128;

    private ImageButton botonPedirCarta;
    private ImageButton botonPlantarse;
    private HBox contenedor;

    public SecciónAcciones() {
        botonPedirCarta = new ImageButton("/recursos/iconos/iconoPedirCarta.png", ALTO_BOTON, ANCHO_BOTON);
        botonPlantarse = new ImageButton("/recursos/iconos/iconoPlantar.png", ALTO_BOTON, ANCHO_BOTON);

        contenedor = new HBox(15, botonPedirCarta, botonPlantarse);
        contenedor.setAlignment(Pos.CENTER);
    }

    public HBox getContenedor() {
        return contenedor;
    }

    public void alPedirCarta(Runnable accion) {
        botonPedirCarta.setOnAction(e -> accion.run());
    }

    public void alPlantarse(Runnable accion) {
        botonPlantarse.setOnAction(e -> accion.run());
    }

    public void habilitarBotones(boolean pedirCartaHabilidado, boolean plantarseHabilitado) {
        botonPedirCarta.setDisable(!pedirCartaHabilidado);
        botonPlantarse.setDisable(!plantarseHabilitado);
    }

}
