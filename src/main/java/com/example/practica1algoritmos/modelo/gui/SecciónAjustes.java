package com.example.practica1algoritmos.modelo.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class SecciónAjustes {
    private final int ALTO_BOTON = 128;
    private final int ANCHO_BOTON = 96;

    private ImageButton botonSalir;
    private ImageButton botonConfiguración;
    private HBox contenedor;

    public SecciónAjustes() {
        botonSalir = new ImageButton("/recursos/iconos/botonSalir.png", ALTO_BOTON, ANCHO_BOTON);
        //botonConfiguración = new ImageButton("/recursos/iconos/iconoConfiguracion.png", ALTO_BOTON, ANCHO_BOTON);

        contenedor = new HBox(10, botonSalir);
        contenedor.setAlignment(Pos.CENTER_LEFT);
    }

    public HBox getContenedor() {
        return contenedor;
    }

    public void alSalir(Runnable accion) {
        botonSalir.setOnAction(e -> accion.run());
    }
}
