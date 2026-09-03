package com.example.practica1algoritmos.modelo.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class SecciónAjustes {
    private final int ALTO_BOTON = 40;
    private final int ANCHO_BOTON = 40;

    private ImageButton botonSalir;
    private ImageButton botonConfiguración;
    private HBox contenedor;

    public SecciónAjustes() {
        botonSalir = new ImageButton("/recursos/iconos/iconoSalir.png", ALTO_BOTON, ANCHO_BOTON);
        botonConfiguración = new ImageButton("/recursos/iconos/configuracion.png", ALTO_BOTON, ANCHO_BOTON);
        botonConfiguración.setDisable(true); // pendiente hasta que acabe las demás clases

        contenedor = new HBox(10, botonConfiguración, botonSalir);
        contenedor.setAlignment(Pos.CENTER_LEFT);
    }

    public HBox getContenedor() {
        return contenedor;
    }

    public void alSalir(Runnable accion) {
        botonSalir.setOnAction(e -> accion.run());
    }
}
