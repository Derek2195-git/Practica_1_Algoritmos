package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.blackjack.Dealer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SecciónDealer {
    private final int ALTO_CARTA = 110;
    private final int ANCHO_CARTA = 70;
    private final int ALTO_ICONO = 96;
    private final int ANCHO_ICONO = 96;

    private ManoGUI mano;
    private HBox contenedor;

    public SecciónDealer(Dealer dealer) {
        mano = new ManoGUI(dealer.getManoJugador(), ALTO_CARTA, ANCHO_CARTA);

        Label nombre = new Label("Dealer");
        ImageView icono = new ImageView(new Image(getClass().getResource("/recursos/iconos/iconoDealer.png").toExternalForm()));
        icono.setFitHeight(ALTO_ICONO);
        icono.setFitWidth(ANCHO_ICONO);
        icono.setPreserveRatio(true);

        contenedor = new HBox(8, icono, nombre, mano.getContenedor());
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getStyleClass().add("seccion-dealer");

    }

    public HBox getContenedor() {
        return contenedor;
    }

    public void redibujarDealer() {
        mano.redibujarMano();
    }
}
