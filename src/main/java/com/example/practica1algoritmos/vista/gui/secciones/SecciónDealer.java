package com.example.practica1algoritmos.vista.gui.secciones;

import com.example.practica1algoritmos.modelo.blackjack.Dealer;
import com.example.practica1algoritmos.vista.gui.objetosGUI.ManoGUI;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SecciónDealer {
    private final int ALTO_CARTA = 110;
    private final int ANCHO_CARTA = 70;
    private final int ALTO_ICONO = 96;
    private final int ANCHO_ICONO = 96;

    private ManoGUI mano;
    private VBox contenedor;
    private Label labelDatos;
    private Dealer dealer;

    public SecciónDealer(Dealer dealer) {
        this.dealer = dealer;
        mano = new ManoGUI(this.dealer.getManoJugador(), ALTO_CARTA, ANCHO_CARTA);

        labelDatos = new Label("Dealer (-)");
        labelDatos.getStyleClass().add("nombre-dealer");
        ImageView icono = new ImageView(new Image(getClass().getResource("/recursos/iconos/iconoDealer.png").toExternalForm()));
        icono.setFitHeight(ALTO_ICONO);
        icono.setFitWidth(ANCHO_ICONO);
        icono.setPreserveRatio(true);

        contenedor = new VBox(8, icono, labelDatos, mano.getContenedor());
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getStyleClass().add("seccion-dealer");


    }

    public VBox getContenedor() {
        return contenedor;
    }

    public void redibujarDealer(boolean esSuTurno) {
        mano.redibujarMano();
        if (esSuTurno) {
            labelDatos.setText("Dealer (" + dealer.getPuntuacion() + ")");
        } else {
            labelDatos.setText("Dealer (-)");
        }

    }
}
