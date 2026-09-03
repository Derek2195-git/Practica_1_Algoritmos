package com.example.practica1algoritmos;

import com.example.practica1algoritmos.controlador.ControladorTerminal;
import com.example.practica1algoritmos.modelo.DeckOfCards.Mazo;
import com.example.practica1algoritmos.modelo.blackjack.BlackjackGame;
import com.example.practica1algoritmos.modelo.blackjack.Jugador;
import com.example.practica1algoritmos.modelo.blackjack.Mano;
import com.example.practica1algoritmos.vista.VistaBlackjackTerminal;
import com.example.practica1algoritmos.vista.gui.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Launcher extends Application {

    private BlackjackGame juego;
    private SecciónDealer seccionDealer;
    private SecciónJugador seccionJugadores;
    private SecciónAcciones seccionAcciones;
    private Label labelResultado;
    private int indiceTurnoActual;

    @Override
    public void start(Stage stage) {
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Derek");
        nombres.add("Kaede");

        juego = new BlackjackGame(nombres);
        juego.repartirCartasIniciales();

        seccionDealer = new SecciónDealer(juego.getDealer());
        seccionJugadores = new SecciónJugador(juego.getJugadores());
        seccionAcciones = new SecciónAcciones();
        labelResultado = new Label("");

        seccionAcciones.alPedirCarta(() -> {
            juego.pedirCarta(indiceTurnoActual);
            refrescarTurnoActual();
        });
        seccionAcciones.alPlantarse(() -> {
            juego.plantarApuesta(indiceTurnoActual);
            refrescarTurnoActual();
        });

        iniciarTurno(0);

        VBox raiz = new VBox(20,
                seccionDealer.getContenedor(),
                seccionAcciones.getContenedor(),
                seccionJugadores.getContenedor(),
                labelResultado);
        raiz.setAlignment(Pos.CENTER);
        raiz.setPadding(new Insets(20));

        Scene escena = new Scene(raiz, 900, 500);
        escena.getStylesheets().add(getClass().getResource("/estilos.css").toExternalForm());
        stage.setScene(escena);
        stage.setTitle("antiarduino llega el profe ibarra y para a omar 'Omar tu no eres asi', 'Tienes razon, pero Omar murió' y entonces omar suelta un rayo" +
                "que desintegra todo el equipamiento del laboratorio D y le quita el pegamento a la protoboard");
        stage.show();

    }

    private void iniciarTurno(int indice) {
        indiceTurnoActual = indice;
        juego.getJugadores().get(indiceTurnoActual).mostrarSusCartas();
        seccionJugadores.redibujar(indiceTurnoActual, true);
    }

    private void refrescarTurnoActual() {
        seccionJugadores.redibujar(indiceTurnoActual, true);

        Jugador jugadorActual = juego.getJugadores().get(indiceTurnoActual);
        if (jugadorActual.isHaTomadoSuTurno()) {
            jugadorActual.ocultarSusCartas();
            avanzarSiguienteTurno();
        }
    }

    private void avanzarSiguienteTurno() {
        int siguiente = indiceTurnoActual + 1;
        if (siguiente < juego.getJugadores().size()) {
            iniciarTurno(siguiente);
        } else {
            seccionJugadores.redibujar(-1, false);
            seccionAcciones.habilitarBotones(false);
            terminarRonda();
        }
    }

    private void terminarRonda() {
        juego.turnoDealer();
        juego.obtenerGanadores();
        juego.revelarCartas();

        seccionDealer.redibujarDealer();
        seccionJugadores.redibujar(-1, false);

        StringBuilder resultado = new StringBuilder();
        juego.getResultadosJugadores().forEach((j, r) ->
                resultado.append(j.getNombreJugador()).append(": ").append(r).append("  "));
        labelResultado.setText(resultado.toString());
    }

    public static void main(String[] args) {
        // Terminal

//        ArrayList<String> nombresJugadores = new ArrayList<>();
//        VistaBlackjackTerminal vista = new VistaBlackjackTerminal();
//        int numeroDeJugadores = vista.pedirNumeroJugadores();
//        for (int i = 0; i < numeroDeJugadores; i++) {
//            nombresJugadores.add(vista.pedirNombreJugador(i));
//        }
//        ControladorTerminal controlador = new ControladorTerminal(nombresJugadores);
//        controlador.iniciarJuego();
        // GUI
        launch();
        //Application.launch(HelloApplication.class, args);
    }

    public void ejecutarGUI(Stage stage) {
        //VentanaConfiguración configuración = new VentanaConfiguración(stage);
        //onfiguración.mostrar();
    }
}
