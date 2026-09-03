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
        VentanaConfiguración ventanaConfiguración = new VentanaConfiguración(stage);
        ventanaConfiguración.mostrar();
    }

    public static void main(String[] args) {
        /*
        Terminal
         */

//        ArrayList<String> nombresJugadores = new ArrayList<>();
//        VistaBlackjackTerminal vista = new VistaBlackjackTerminal();
//        int numeroDeJugadores = vista.pedirNumeroJugadores();
//        for (int i = 0; i < numeroDeJugadores; i++) {
//            nombresJugadores.add(vista.pedirNombreJugador(i));
//        }
//        ControladorTerminal controlador = new ControladorTerminal(nombresJugadores);
//        controlador.iniciarJuego();
        /*
        GUI
         */
        launch();
    }
}
