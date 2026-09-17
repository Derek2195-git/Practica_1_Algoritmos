package com.example.practica1algoritmos.vista.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class SecciónAjustes {
    private final int ALTO_BOTON = 64;
    private final int ANCHO_BOTON = 64;

    private ImageButton botonSalir;
    private ImageButton botonConfiguración;
    private ImageButton botonUndo;
    private HBox contenedor;

    public SecciónAjustes() {
        botonSalir = new ImageButton("/recursos/iconos/botonSalir.png", ALTO_BOTON*2, ANCHO_BOTON*3/2);
            botonConfiguración = new ImageButton("/recursos/iconos/botonConfiguracion.png", ALTO_BOTON, ANCHO_BOTON);
        botonUndo = new ImageButton("/recursos/iconos/botonUndo.png", ALTO_BOTON, ANCHO_BOTON );

        contenedor = new HBox(10, botonSalir, botonConfiguración,    botonUndo);
        contenedor.setAlignment(Pos.CENTER_LEFT);
        contenedor.setPadding(new Insets(30, 0, 0, 20));
    }

    public HBox getContenedor() {
        return contenedor;
    }

    public void alSalir(Runnable accion) {
        botonSalir.setOnAction(e -> accion.run());
    }

    public void alDeshacer(Runnable accion) { botonUndo.setOnAction(e -> accion.run()); }

    public void habilitarDeshacer(boolean habilitado) {
        botonUndo.setDisable(!habilitado);
    }
}
