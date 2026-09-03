package com.example.practica1algoritmos.vista.gui;

import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Optional;

public class VentanaJuego {

    private Stage stage;
    private BlackjackGame juego;
    private SecciónAjustes secciónAjustes;
    private SecciónDealer seccionDealer;
    private SecciónAcciones seccionAcciones;
    private SecciónJugador seccionJugador;
    private Label labelResultado;

    public VentanaJuego(Stage stage, BlackjackGame juego) {
        this.stage = stage;
        this.juego = juego;

        secciónAjustes = new SecciónAjustes();
        seccionDealer = new SecciónDealer(juego.getDealer());
        seccionAcciones = new SecciónAcciones();
        seccionJugador = new SecciónJugador(juego.getJugadores());
        labelResultado = new Label("");

        secciónAjustes.alSalir(this::volverAVentanaAnterior);
    }

    public void mostrar() {
        VBox raiz = new VBox(15, secciónAjustes.getContenedor(), seccionDealer.getContenedor(),
                seccionAcciones.getContenedor(), seccionJugador.getContenedor(), labelResultado);
        raiz.setAlignment(Pos.CENTER);
        raiz.setPadding(new Insets(20));

        Scene escena = new Scene(raiz, 800, 600);
        escena.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
        stage.setScene(escena);
        stage.setTitle("BlackJack");
        stage.show();
    }

    public void volverAVentanaAnterior() {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Seguro que quieres salir? Se perderá el progreso de la partida.");
        Optional<ButtonType> respuesta = confirmacion.showAndWait();

        if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
            VentanaConfiguración configuracion = new VentanaConfiguración(stage);
            configuracion.mostrar();
        }
    }

    public void alPedirCarta(Runnable accion) {
        seccionAcciones.alPedirCarta(accion);
    }

    public void alPlantarse(Runnable accion) {
        seccionAcciones.alPlantarse(accion);
    }

    public void actualizarJugadores(int indiceTurnoActual, boolean hayJugadorEnTurno) {
        seccionJugador.redibujar(indiceTurnoActual, hayJugadorEnTurno);
    }

    public void actualizarDealer() {
        seccionDealer.redibujarDealer();
    }

    public void habilitarAcciones(boolean habilitado) {
        seccionAcciones.habilitarBotones(habilitado);
    }

    public void mostrarResultados(HashMap<Jugador, String> resultados) {
        StringBuilder texto = new StringBuilder();
        resultados.forEach((jugador, resultado) ->
                texto.append(jugador.getNombreJugador()).append(": ").append(resultado).append("  "));
        labelResultado.setText(texto.toString());
    }

}
