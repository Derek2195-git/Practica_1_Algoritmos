package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaJuego {

    private Stage stage;
    private BlackjackGame juego;

    public VentanaJuego(Stage stage, BlackjackGame juego) {
        this.stage = stage;
        this.juego = juego;
    }

    public void mostrar() {
        Label texto = new Label("Partida iniciada con " + " jugadores");
        VBox raiz = new VBox(texto);
        raiz.setAlignment(Pos.CENTER);

        Scene escena = new Scene(raiz, 500, 500);
        stage.setScene(escena);
        stage.setTitle("Juego iniciado");
        stage.show();
    }
}
