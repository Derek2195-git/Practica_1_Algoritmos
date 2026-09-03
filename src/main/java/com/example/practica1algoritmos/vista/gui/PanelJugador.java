package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelJugador {
    private final int ALTO_PANEL = 60;
    private final int ANCHO_PANEL = 60;
    private final int ALTO_CARTA = 70;
    private final int ANCHO_CARTA = 45;

    private Jugador jugador;
    private ManoGUI mano;
    private VBox contenedor;

    public PanelJugador(Jugador jugador, int indiceJugador) {
        this.jugador = jugador;
        mano = new ManoGUI(jugador.getManoJugador(), ALTO_CARTA,ANCHO_CARTA);

        String rutaIcono = "/recursos/iconos/iconoJugador" + (indiceJugador + 1) + ".png";
        ImageView icono = new ImageView(new Image(getClass().getResource(rutaIcono).toExternalForm()));
        icono.setFitHeight(ALTO_PANEL);
        icono.setFitWidth(ANCHO_PANEL);
        icono.setPreserveRatio(true);

        Label nombre = new Label(jugador.getNombreJugador());
        contenedor = new VBox(6, icono, nombre, mano.getContenedor());
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getStyleClass().add("panel-jugador");
    }

    public void redibujar(boolean esSuTurno) {
        mano.redibujarMano();

        if (esSuTurno) {
            if (!contenedor.getStyleClass().contains("jugador-en-turno-propio")) {
                contenedor.getStyleClass().add("jugador-en-turno-propio");
            }
        } else {
            contenedor.getStyleClass().remove("jugador-en-turno-propio");
        }
    }

    public VBox getContenedor() { return contenedor; }
}
