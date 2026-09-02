package com.example.practica1algoritmos.vista.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VentanaConfiguración {
    private final int MAX_JUGADORES = 4;
    private final int MIN_JUGADORES = 1;

    private Stage ventana;
    private VBox contenedorNombres;
    private ArrayList<TextField> camposNombre;
    private Button botonIniciar;

    public VentanaConfiguración(Stage ventana) {
        this.ventana = ventana;
        contenedorNombres = new VBox(8);
        camposNombre = new ArrayList<>();
        botonIniciar = new Button("Empezar partida");
    }

    public void mostrar() {
        Label titulo = new Label("Blackjack");

        Slider sliderJugadores = new Slider(MIN_JUGADORES, MAX_JUGADORES, MIN_JUGADORES);
        sliderJugadores.setMajorTickUnit(1);
        sliderJugadores.setMinorTickCount(0);
        sliderJugadores.setSnapToTicks(true);
        sliderJugadores.setShowTickLabels(true);
        sliderJugadores.setShowTickMarks(true);

        Label labelCantidad = new Label("Numero de júgadores: " + MIN_JUGADORES);

        sliderJugadores.valueProperty().addListener((obs, valorAnterior, valorNuevo) -> {
            int cantidad = valorNuevo.intValue();
            labelCantidad.setText("Número de jugadores");
            // Aqui va un redraw
        });

        contenedorNombres.setAlignment(Pos.CENTER);
        // Quizas otro redraw aqui para el boton?

        botonIniciar.setDisable(true);
        botonIniciar.setOnAction(e -> iniciarPartida());

        VBox root = new VBox(15, titulo, labelCantidad, sliderJugadores, contenedorNombres, botonIniciar);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Scene escena = new Scene(root, 800, 600);
        ventana.setScene(escena);
        ventana.setTitle("Configuración del 21");
        ventana.show();

    }
}
